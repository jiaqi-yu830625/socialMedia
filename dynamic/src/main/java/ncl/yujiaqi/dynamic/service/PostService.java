package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * post table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostService extends IService<Post>  {


    Post add(Post post);

    Post update(Post post);

    Boolean delete(Long id);
}
