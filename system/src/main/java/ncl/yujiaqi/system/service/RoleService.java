package ncl.yujiaqi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.system.domain.entity.Role;

/**
 * role table 服务类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface RoleService extends IService<Role> {


    Role add(Role role);

    Role update(Role role);

    Boolean delete(Long id);
}
