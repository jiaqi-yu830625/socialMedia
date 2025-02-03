package ncl.yujiaqi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.system.domain.entity.Role;
import ncl.yujiaqi.system.mapper.RoleMapper;
import ncl.yujiaqi.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * role table 服务实现类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public Role add(Role role) {
        baseMapper.insert(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        baseMapper.updateById(role);
        return role;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
