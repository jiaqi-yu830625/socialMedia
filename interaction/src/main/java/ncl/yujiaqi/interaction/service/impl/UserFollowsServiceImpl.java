package ncl.yujiaqi.interaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.interaction.domain.dto.UserFollowDTO;
import ncl.yujiaqi.interaction.domain.entity.UserFollows;
import ncl.yujiaqi.interaction.mapper.UserFollowsMapper;
import ncl.yujiaqi.interaction.service.UserFollowsService;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // check followUser valid
        User followUser = userService.getById(followUserId);
        if (followUser == null || followUser.getDeleted() == 1) {
            throw SMException.build(ResultEnum.DATA_NOT_FOUND, "This user isn't exist!");
        }
        UserDTO userDTO = userService.getCurrentUser();
        Long userId = userDTO.getId();

        return new UserFollows(userId, followUserId);
    }

    @Override
    public Boolean cancelById(Long followUserId) {
        UserDTO userDTO = userService.getCurrentUser();
        Long userId = userDTO.getId();
        UserFollows userFollows = baseMapper.selectByUserAndFollowUser(userId, followUserId);
        if (userFollows != null && userFollows.getId() != null) {
            delete(userFollows.getId());
        }
        return true;
    }

    @Override
    public UserFollowDTO getFollowsByUserId(Long userId) {
        UserFollowDTO userFollowDTO = new UserFollowDTO();
        userFollowDTO.setUserId(userId);

        List<UserFollows> userFollows = baseMapper.selectByUserId(userId);
        List<Long> followerIds = Optional.ofNullable(userFollows).orElseGet(ArrayList::new).stream().map(UserFollows::getFollowerId).collect(Collectors.toList());
        if (followerIds.isEmpty()) {
            return userFollowDTO;
        }
        UserDTO loginUser = userService.getCurrentUser();

        List<UserDTO> userDTOS = userService.listByIds(followerIds).stream().map(user -> userService.convert(user)).collect(Collectors.toList());
        userFollowDTO.setFollowUsers(userDTOS)
                .setFollowerIds(userDTOS.stream().map(UserDTO::getId).collect(Collectors.toList()))
                .setFollowed(userDTOS.stream().map(UserDTO::getId).anyMatch(id -> loginUser.getId().equals(id)));
        return userFollowDTO;
    }
}
