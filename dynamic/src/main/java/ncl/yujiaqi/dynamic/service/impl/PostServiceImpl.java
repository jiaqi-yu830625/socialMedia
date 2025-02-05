package ncl.yujiaqi.dynamic.service.impl;

import ncl.yujiaqi.dynamic.domain.entity.Post;
import ncl.yujiaqi.dynamic.mapper.PostMapper;
import ncl.yujiaqi.dynamic.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * post table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {


    @Override
    public Post add(Post post) {
        baseMapper.insert(post);
        return post;
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
}
