package ncl.yujiaqi.dynamic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.dynamic.domain.entity.PostLikes;
import ncl.yujiaqi.dynamic.mapper.PostLikesMapper;
import ncl.yujiaqi.dynamic.service.PostLikesService;
import ncl.yujiaqi.dynamic.service.PostService;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * post likes table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class PostLikesServiceImpl extends ServiceImpl<PostLikesMapper, PostLikes> implements PostLikesService {

    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

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
        if(postIds.isEmpty()){
            return new ArrayList<>(0);
        }
        return baseMapper.selectByPostIds(postIds);
    }

    @Override
    public List<PostLikes> selectByPostId(Long postId) {
        return baseMapper.selectByPostId(postId);
    }

    @Override
    public void deleteByPostId(Long postId) {
        baseMapper.deleteByPostId(postId);
    }

    @Override
    public PostLikes addByPostId(Long postId) {
        PostLikes postLikes = new PostLikes();
        Long userId = userService.getCurrentUser().getId();
        if (postService.getById(postId) == null) {
            throw SMException.build(ResultEnum.DATA_NOT_FOUND, "post not exist!");
        }
        List<PostLikes> originLikes = baseMapper.selectByUserAndPost(userId, postId);
        if (originLikes.size() > 0) {
            return originLikes.get(0);
        }
        postLikes.setPostId(postId)
                .setUserId(userId);
        add(postLikes);
        return postLikes;
    }

    @Override
    public void deleteByUserAndPost(Long postId) {
        Long userId = userService.getCurrentUser().getId();
        if (postService.getById(postId) == null) {
            throw SMException.build(ResultEnum.DATA_NOT_FOUND, "post not exist!");
        }

        List<PostLikes> originLikes = baseMapper.selectByUserAndPost(userId, postId);
        if (originLikes.size() > 0) {
            baseMapper.deleteBatchIds(originLikes.stream().map(PostLikes::getId).collect(toList()));
        }
    }
}
