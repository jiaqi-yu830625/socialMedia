package ncl.yujiaqi.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.dynamic.domain.entity.PostImgData;

/**
 * file table
 *
 * @author yujiaqi
 * @since 2025-02-06
 */
public interface PostImgDataService extends IService<PostImgData>  {


    PostImgData add(PostImgData imgData);

    PostImgData update(PostImgData imgData);

    Boolean delete(Long id);
}
