package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
