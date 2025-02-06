package ncl.yujiaqi.dynamic.service.impl;

import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import ncl.yujiaqi.dynamic.mapper.PostLikesMapper;
import ncl.yujiaqi.dynamic.service.PostLikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * post likes table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostLikesServiceImpl extends ServiceImpl<PostLikesMapper, PostLikes> implements PostLikesService {


    @Override
    public PostLikes add(PostLikes postLikes) {
        baseMapper.insert(postLikes);
        return postLikes;
    }

    @Override
    public PostLikes update(PostLikes postLikes) {
        baseMapper.updateById(postLikes);
        return postLikes;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<PostLikes> selectByPostIds(List<Long> postIds) {
        return baseMapper.selectByPostIds(postIds);
    }

    @Override
    public List<PostLikes> selectByPostId(Long postId) {
        return baseMapper.selectByPostId(postId);
    }
}
