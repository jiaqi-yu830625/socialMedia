package ncl.yujiaqi.interaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.interaction.domain.entity.UserFollows;
import ncl.yujiaqi.interaction.mapper.UserFollowsMapper;
import ncl.yujiaqi.interaction.service.UserFollowsService;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user follows table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@Service
public class UserFollowsServiceImpl extends ServiceImpl<UserFollowsMapper, UserFollows> implements UserFollowsService {

    @Resource
    private UserService userService;

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

    @Override
    public UserFollows addById(Long followUserId) {
        UserDTO userDTO = userService.getCurrentUser();
        Long userId = userDTO.getId();
//todo
        return new UserFollows();
    }
}
