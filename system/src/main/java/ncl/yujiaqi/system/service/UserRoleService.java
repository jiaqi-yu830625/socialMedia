package ncl.yujiaqi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.system.domain.entity.UserRole;

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
}
