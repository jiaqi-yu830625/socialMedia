package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * post likes table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostLikesService extends IService<PostLikes>  {


    PostLikes add(PostLikes postLikes);

    PostLikes update(PostLikes postLikes);

    Boolean delete(Long id);

    List<PostLikes> selectByPostIds(List<Long> postIds);

    List<PostLikes> selectByPostId(Long postId);

    void deleteByPostId(Long postId);

    PostLikes addByPostId(Long postId);

    void deleteByUserAndPost(Long postId);
}
