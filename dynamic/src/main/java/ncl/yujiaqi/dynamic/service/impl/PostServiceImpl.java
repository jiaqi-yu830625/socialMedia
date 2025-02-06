package ncl.yujiaqi.dynamic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.dynamic.domain.dto.CommentUserDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostCommentDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostDTO;
import ncl.yujiaqi.dynamic.domain.entity.Post;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import ncl.yujiaqi.dynamic.mapper.PostMapper;
import ncl.yujiaqi.dynamic.service.PostCommentService;
import ncl.yujiaqi.dynamic.service.PostImgService;
import ncl.yujiaqi.dynamic.service.PostLikesService;
import ncl.yujiaqi.dynamic.service.PostService;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.util.Pair.toMap;

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
        if (CollectionUtil.isNotEmpty(postDto.getImageList())) {
            postImgService.addList(post.getId(), postDto.getImageList());
        }
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
        Map<Long, List<PostImg>> imgMap = postImgService.selectByPostIds(postIds).stream()
                .collect(groupingBy(PostImg::getPostId));

        List<PostDTO> dtoList = new ArrayList<>(posts.size());
        posts.forEach(post -> {
            List<String> imgList = imgMap.get(post.getId()).stream().map(PostImg::getUrl).collect(toList());
            dtoList.add(new PostDTO(post.getId(), post.getUserId(), post.getContent(), imgList, post.getCreateTime()));
        });
        return dtoList;
    }

    @Override
    public List<PostDTO> pageByUserId(Long userId) {
        List<PostDTO> dtos = getByUserId(userId);
        List<Long> postIds = dtos.stream().map(PostDTO::getId).collect(toList());

        // search post-likes and post-comments
        Map<Long, List<PostLikes>> likesMap = postLikesService.selectByPostIds(postIds).stream().collect(groupingBy(PostLikes::getPostId));

        // search post-comments
        Map<Long, List<PostComment>> commentsMap = postCommentService.selectByPostIds(postIds).stream().collect(groupingBy(PostComment::getPostId));
        Map<Long, List<PostCommentDTO>> commentsDtoMap = new HashMap<>(commentsMap.size());
        commentsMap.forEach((postId, comments) ->
                commentsDtoMap.put(postId, postCommentService.buildCommentTree(comments)));

        dtos.forEach(dto ->
                dto.setLikesNumber(likesMap.get(dto.getId()).size())
                        .setPostComments(commentsDtoMap.get(dto.getId())));

        // sort by createTime desc
        dtos = dtos.stream().sorted(Comparator.comparing(PostDTO::getCreateTime).reversed()).collect(toList());
        return dtos;
    }

    @Override
    public PostDTO selectById(Long id) {
        Post post = getById(id);

        // search images
        List<String> postImgs = postImgService.selectByPostId(post.getId()).stream().map(PostImg::getUrl).collect(toList());

        // search post-likes and post-comments
        List<PostLikes> postLikes = postLikesService.selectByPostId(post.getId());
        List<PostComment> postComments = postCommentService.selectByPostId(post.getId());
        List<PostCommentDTO> postCommentDTOS = postCommentService.buildCommentTree(postComments);

        // search comments users
        List<Long> commentUserIds = postComments.stream().map(PostComment::getUserId).collect(toList());
        List<CommentUserDTO> commentUserDTOS = postCommentService.listCommentUserByIds(commentUserIds);

        PostDTO postDTO = new PostDTO(post.getId(), post.getUserId(), post.getContent(), postImgs, post.getCreateTime());
        postDTO.setUser(userService.getById(postDTO.getUserId()))
                .setPostLikes(postLikes)
                .setPostComments(postCommentDTOS)
                .setLikesNumber(postLikes.size())
                .setCommentUsers(commentUserDTOS);

        return postDTO;
    }

    public Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        BeanUtil.copyProperties(postDTO, post);
        return post;
    }
}
