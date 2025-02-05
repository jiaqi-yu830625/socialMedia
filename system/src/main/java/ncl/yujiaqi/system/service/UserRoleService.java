package ncl.yujiaqi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.system.domain.entity.Role;
import ncl.yujiaqi.system.domain.entity.UserRole;

import java.util.List;
import java.util.Set;

/**
 * user role table 服务类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface UserRoleService extends IService<UserRole> {


    UserRole add(UserRole userRole);

    UserRole update(UserRole userRole);

    Boolean delete(Long id);

    List<Role> selectByUserId(Long id);
}
