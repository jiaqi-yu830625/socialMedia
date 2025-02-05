package ncl.yujiaqi.dynamic.service.impl;

import ncl.yujiaqi.dynamic.domain.entity.PostImg;
import ncl.yujiaqi.dynamic.mapper.PostImgMapper;
import ncl.yujiaqi.dynamic.service.PostImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * post image table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostImgServiceImpl extends ServiceImpl<PostImgMapper, PostImg> implements PostImgService {


    @Override
    public PostImg add(PostImg postImg) {
        baseMapper.insert(postImg);
        return postImg;
    }

    @Override
    public PostImg update(PostImg postImg) {
        baseMapper.updateById(postImg);
        return postImg;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
