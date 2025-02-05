package ncl.yujiaqi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.system.domain.entity.Role;
import ncl.yujiaqi.system.domain.entity.UserRole;
import ncl.yujiaqi.system.mapper.UserRoleMapper;
import ncl.yujiaqi.system.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * user role table 服务实现类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    @Override
    public UserRole add(UserRole userRole) {
        baseMapper.insert(userRole);
        return userRole;
    }

    @Override
    public UserRole update(UserRole userRole) {
        baseMapper.updateById(userRole);
        return userRole;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<Role> selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }


}
