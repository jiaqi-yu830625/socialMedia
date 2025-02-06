package ncl.yujiaqi.dynamic.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import ncl.yujiaqi.dynamic.domain.dto.CommentUserDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostCommentDTO;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import ncl.yujiaqi.dynamic.mapper.PostCommentMapper;
import ncl.yujiaqi.dynamic.service.PostCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * post comment table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements PostCommentService {

    @Resource
    private UserService userService;

    @Override
    public PostComment add(PostComment postComment) {
        baseMapper.insert(postComment);
        return postComment;
    }

    @Override
    public PostComment update(PostComment postComment) {
        baseMapper.updateById(postComment);
        return postComment;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<PostComment> selectByPostIds(List<Long> postIds) {
        return baseMapper.selectByPostIds(postIds);
    }

    @Override
    public List<PostCommentDTO> buildCommentTree(List<PostComment> comments) {
        if (CollectionUtil.isEmpty(comments)) {
            return new ArrayList<>(0);
        }
        Map<Long, PostCommentDTO> commentMap = comments.stream()
                .map(comment -> new PostCommentDTO(comment, null))
                .collect(toMap(dto -> dto.getPostComment().getId(), Function.identity()));

        // storage root comment
        List<PostCommentDTO> rootComments = new ArrayList<>();

        // group the child-comment
        for (PostComment comment : comments) {
            PostCommentDTO dto = commentMap.get(comment.getId());
            Long parentId = dto.getPostComment().getParentCommentId();
            if (parentId == null) {
                rootComments.add(dto);
            } else {
                // add children
                PostCommentDTO parent = commentMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(comment);
                }
            }
        }
        return rootComments;
    }

    @Override
    public List<PostComment> selectByPostId(Long postId) {
        return baseMapper.selectByPostId(postId);
    }

    @Override
    public List<CommentUserDTO> listCommentUserByIds(List<Long> userIds) {
        if (CollectionUtil.isEmpty(userIds)) {
            return new ArrayList<>(0);
        }
        List<User> users = userService.listByIds(userIds);
        List<CommentUserDTO> dtos = new ArrayList<>(users.size());

        users.forEach(user ->
                dtos.add(new CommentUserDTO(user.getId(), user.getUsername(), user.getAvatar()))
        );
        return dtos;
    }
}
