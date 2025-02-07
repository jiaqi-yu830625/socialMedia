package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.dto.CommentUserDTO;
import ncl.yujiaqi.dynamic.domain.dto.PostCommentDTO;
import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * post comment table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostCommentService extends IService<PostComment>  {


    PostComment add(PostComment postComment);

    PostComment update(PostComment postComment);

    Boolean delete(Long id);

    List<PostComment> selectByPostIds(List<Long> postIds);

    List<PostCommentDTO> buildCommentTree(List<PostComment> comments);

    List<PostComment> selectByPostId(Long postId);

    List<CommentUserDTO> listCommentUserByIds(List<Long> userIds);

    void deleteByPostId(Long postId);

    PostComment addComment(Long postId, Long sourceId, String comment);
}
