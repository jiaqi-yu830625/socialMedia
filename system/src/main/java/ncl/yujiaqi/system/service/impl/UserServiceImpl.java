package ncl.yujiaqi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.mapper.UserMapper;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * user table 服务实现类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User add(User user) {
        baseMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        baseMapper.updateById(user);
        return user;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
