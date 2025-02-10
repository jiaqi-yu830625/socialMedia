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
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

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
    public List<PostDTO> pageByUserId(Long userId) {
        List<Post> posts = baseMapper.getByUserId(userId);
        List<PostDTO> dtos = getByUserList(posts);
        // sort by createTime desc
        dtos = dtos.stream().sorted(Comparator.comparing(PostDTO::getCreateTime).reversed()).collect(toList());
        return dtos;
    }

    @Override
    public PostDTO selectById(Long id) {
        Post post = getById(id);
        List<PostDTO> dtos = getByUserList(Collections.singletonList(post));

        return dtos.get(0);
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

    @Override
    public List<PostDTO> pageAll() {
        List<Post> posts = baseMapper.selectAll();
        return getByUserList(posts);
    }

    public List<PostDTO> getByUserList(List<Post> posts) {
        if (posts.isEmpty()) {
            return new ArrayList<>(0);
        }
        UserDTO userDTO = userService.getCurrentUser();
        List<PostDTO> dtoList = new ArrayList<>(posts.size());

        List<Long> postIds = posts.stream().map(Post::getId).collect(toList());

        // 1- get postImg and imgData
        List<PostImg> postImgs = postImgService.selectByPostIds(postIds);
        List<Long> postImgIds = postImgs.stream().map(PostImg::getFileId).collect(toList());
        List<PostImgData> imgDataList;
        if (!postImgs.isEmpty()) {
            imgDataList = postImgDataService.listByIds(postImgIds);
        } else {
            imgDataList = new ArrayList<>();
        }
        Map<Long, List<PostImg>> imgMap = postImgs.stream().collect(groupingBy(PostImg::getPostId));

        // 2- get like
        Map<Long, List<PostLikes>> likesMap = postLikesService.selectByPostIds(postIds).stream().collect(groupingBy(PostLikes::getPostId));

        // 3- get comment
        List<PostComment> postComments = postCommentService.selectByPostIds(postIds);
        Map<Long, List<PostCommentDTO>> commentDTOMap = postCommentService.buildCommentTree(postComments).stream().collect(groupingBy(dto -> dto.getPostComment().getPostId()));

        // 4- get user
        List<Long> userList = posts.stream().map(Post::getUserId).collect(toList());
        Map<Long, User> userMap = userService.listByIds(userList).stream().collect(toMap(User::getId, Function.identity()));

        posts.forEach(post -> {
            // get postImg and imgData
            List<Long> imgList = Optional.ofNullable(imgMap.get(post.getId())).orElseGet(ArrayList::new).stream().map(PostImg::getFileId).collect(toList());
            List<PostImgData> dataList = imgDataList.stream().filter(data -> imgList.contains(data.getId())).collect(toList());

            // get like and comment
            List<PostLikes> postLikes = likesMap.get(post.getId());
            List<PostCommentDTO> commentDTOs = commentDTOMap.get(post.getId());
            int commentNum = 0;
            if (commentDTOs != null) {
                AtomicInteger countNum = new AtomicInteger();
                commentDTOs.stream().map(PostCommentDTO::getChildren).forEach(a -> countNum.addAndGet(Optional.ofNullable(a).orElseGet(ArrayList::new).size()));
                commentNum = countNum.get() + commentDTOs.size();
            }

            // get user
            User user = userMap.get(post.getUserId());

            PostDTO postDTO = new PostDTO();
            postDTO
                    .setUserId(post.getUserId())
                    .setContent(post.getContent())
                    .setImageDataIdList(imgList)
                    .setImgDataList(dataList)
                    .setUser(user)
                    .setLikesNumber(postLikes == null ? 0 : postLikes.size())
                    .setCommentNumber(commentNum)
                    .setPostLikes(postLikes)
                    .setPostComments(commentDTOs)
                    .setLiked(Optional.ofNullable(postLikes).orElseGet(ArrayList::new).stream().anyMatch(l -> l.getUserId().equals(userDTO.getId())))
                    .setId(post.getId())
                    .setCreateTime(post.getCreateTime())
                    .setUpdateTime(post.getUpdateTime());

            dtoList.add(postDTO);
        });
        return dtoList;

    }

    public Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        BeanUtil.copyProperties(postDTO, post);
        return post;
    }
}
