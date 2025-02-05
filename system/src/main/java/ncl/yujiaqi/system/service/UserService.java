package ncl.yujiaqi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.system.domain.dto.LoginDTO;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.domain.dto.UserTokenDTO;
import ncl.yujiaqi.system.domain.entity.User;

/**
 * user table 服务类
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface UserService extends IService<User> {


    User add(User user);

    User update(User user);

    Boolean delete(Long id);

    UserTokenDTO login(LoginDTO loginDTO);

    boolean updatePassword(Long id, String password);

    UserDTO getCurrentUser();

    UserDTO convert(User user);
}
