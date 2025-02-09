package ncl.yujiaqi.dynamic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.dynamic.domain.dto.CommentUserDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostCommentDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostDTO;
import ncl.yujiaqi.dynamic.domain.entity.*;
import ncl.yujiaqi.dynamic.mapper.PostMapper;
import ncl.yujiaqi.dynamic.service.*;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * post table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Resource
    private PostMapper postMapper;
    @Resource
    private PostImgService postImgService;
    @Resource
    private PostImgDataService postImgDataService;
    @Resource
    private PostLikesService postLikesService;
    @Resource
    private PostCommentService postCommentService;
    @Resource
    private UserService userService;

    @Override
    public PostDTO add(PostDTO postDto) {
        Post post = convertToEntity(postDto);
        baseMapper.insert(post);
        // save postImages
        if (CollectionUtil.isNotEmpty(postDto.getImageDataIdList())) {
            postImgService.addList(post.getId(), postDto.getImageDataIdList());
        }
        postDto.setUser(userService.getById(postDto.getUserId()))
                .setId(post.getId());

        return postDto;
    }

    @Override
    public Post update(Post post) {
        baseMapper.updateById(post);
        return post;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<PostDTO> getByUserId(Long userId) {
        List<Post> posts = postMapper.getByUserId(userId);
        // search postImgs with postIdList
        List<Long> postIds = posts.stream().map(Post::getId).collect(toList());

        // search imgDataList
        List<PostImg> postImgs = postImgService.selectByPostIds(postIds);
        List<Long> imgDataIds = postImgs.stream().map(PostImg::getFileId).collect(toList());
        List<PostImgData> imgDataList;
        if (!imgDataIds.isEmpty()) {
            imgDataList = postImgDataService.listByIds(imgDataIds);
        } else {
            imgDataList = new ArrayList<>();
        }

        Map<Long, List<PostImg>> imgMap = postImgs.stream().collect(groupingBy(PostImg::getPostId));
        List<PostDTO> dtoList = new ArrayList<>(posts.size());
        posts.forEach(post -> {
            List<Long> imgList = Optional.ofNullable(imgMap.get(post.getId())).orElseGet(ArrayList::new).stream().map(PostImg::getFileId).collect(toList());
            List<PostImgData> dataList = imgDataList.stream().filter(data -> imgList.contains(data.getId())).collect(toList());
            dtoList.add(new PostDTO(post.getId(), post.getUserId(), post.getContent(), imgList, dataList, post.getCreateTime()));
        });
        return dtoList;
    }

    @Override
    public List<PostDTO> pageByUserId(Long userId) {
        List<PostDTO> dtos = getByUserId(userId);
        List<Long> postIds = dtos.stream().map(PostDTO::getId).collect(toList());

        // search post-likes
        Map<Long, List<PostLikes>> likesMap = postLikesService.selectByPostIds(postIds).stream().collect(groupingBy(PostLikes::getPostId));

        // search post-comments
        List<PostComment> postComments = postCommentService.selectByPostIds(postIds);
        Map<Long, List<PostComment>> commentsMap = postComments.stream().collect(groupingBy(PostComment::getPostId));
        Map<Long, List<PostCommentDTO>> commentsDtoMap = new HashMap<>(commentsMap.size());
        commentsMap.forEach((postId, comments) ->
                commentsDtoMap.put(postId, postCommentService.buildCommentTree(comments)));

        dtos.forEach(dto ->
                dto.setLikesNumber(Optional.ofNullable(likesMap.get(dto.getId())).orElseGet(ArrayList::new).size())
                        .setCommentNumber(postComments.size())
                        .setPostComments(commentsDtoMap.get(dto.getId()))
        );

        // sort by createTime desc
        dtos = dtos.stream().sorted(Comparator.comparing(PostDTO::getCreateTime).reversed()).collect(toList());
        return dtos;
    }

    @Override
    public PostDTO selectById(Long id) {
        Post post = getById(id);

        // search images
        List<Long> postImgs = postImgService.selectByPostId(post.getId()).stream().map(PostImg::getFileId).collect(toList());
        List<PostImgData> imgDataList = new ArrayList<>();
        if (!postImgs.isEmpty()) {
            imgDataList = postImgDataService.listByIds(postImgs);
        }

        // search post-likes and post-comments
        List<PostLikes> postLikes = postLikesService.selectByPostId(post.getId());
        List<PostComment> postComments = postCommentService.selectByPostId(post.getId());
        List<PostCommentDTO> postCommentDTOS = postCommentService.buildCommentTree(postComments);

        // search comments users
        List<Long> commentUserIds = postComments.stream().map(PostComment::getUserId).collect(toList());
        List<CommentUserDTO> commentUserDTOS = postCommentService.listCommentUserByIds(commentUserIds);

        PostDTO postDTO = new PostDTO(post.getId(), post.getUserId(), post.getContent(), postImgs, imgDataList, post.getCreateTime());
        postDTO.setUser(userService.getById(postDTO.getUserId()))
                .setPostLikes(postLikes)
                .setPostComments(postCommentDTOS)
                .setLikesNumber(postLikes.size())
                .setCommentUsers(commentUserDTOS);

        return postDTO;
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        // check post's user valid
        Long userId = userService.getCurrentUser().getId();
        Post post = getById(id);
        if (!post.getUserId().equals(userId)) {
            throw SMException.build(ResultEnum.AUTH_FAILED, "you cannot delete this post!");
        }

        // delete images
        postImgService.deleteByPostId(id);
        // delete likes
        postLikesService.deleteByPostId(id);
        // delete comments
        postCommentService.deleteByPostId(id);
        // delete post
        baseMapper.delById(id);

        return true;
    }

    public Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        BeanUtil.copyProperties(postDTO, post);
        return post;
    }
}
