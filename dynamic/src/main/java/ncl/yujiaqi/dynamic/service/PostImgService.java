package ncl.yujiaqi.dynamic.service;

import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * post image table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface PostImgService extends IService<PostImg>  {


    PostImg add(PostImg postImg);

    PostImg update(PostImg postImg);

    Boolean delete(Long id);
}
