package ncl.yujiaqi.dynamic.service.impl;

import ncl.yujiaqi.dynamic.domain.entity.PostImgData;
import ncl.yujiaqi.dynamic.mapper.PostImgDataMapper;
import ncl.yujiaqi.dynamic.service.PostImgDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * file table
 *
 * @author yujiaqi
 * @since 2025-02-06
 */
@Service
public class PostImgDataServiceImpl extends ServiceImpl<PostImgDataMapper, PostImgData> implements PostImgDataService {


    @Override
    public PostImgData add(PostImgData imgData) {
        baseMapper.insert(imgData);
        return imgData;
    }

    @Override
    public PostImgData update(PostImgData imgData) {
        baseMapper.updateById(imgData);
        return imgData;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
