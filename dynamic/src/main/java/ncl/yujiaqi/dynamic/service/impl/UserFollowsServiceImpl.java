package ncl.yujiaqi.dynamic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.dynamic.domain.entity.UserFollows;
import ncl.yujiaqi.dynamic.mapper.UserFollowsMapper;
import ncl.yujiaqi.dynamic.service.UserFollowsService;
import org.springframework.stereotype.Service;

/**
 * user follows table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class UserFollowsServiceImpl extends ServiceImpl<UserFollowsMapper, UserFollows> implements UserFollowsService {


    @Override
    public UserFollows add(UserFollows userFollows) {
        baseMapper.insert(userFollows);
        return userFollows;
    }

    @Override
    public UserFollows update(UserFollows userFollows) {
        baseMapper.updateById(userFollows);
        return userFollows;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
