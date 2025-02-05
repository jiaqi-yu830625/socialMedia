package ncl.yujiaqi.dynamic.service.impl;

import ncl.yujiaqi.dynamic.domain.entity.PostComment;
import ncl.yujiaqi.dynamic.mapper.PostCommentMapper;
import ncl.yujiaqi.dynamic.service.PostCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * post comment table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment> implements PostCommentService {


    @Override
    public PostComment add(PostComment postComment) {
        baseMapper.insert(postComment);
        return postComment;
    }

    @Override
    public PostComment update(PostComment postComment) {
        baseMapper.updateById(postComment);
        return postComment;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
