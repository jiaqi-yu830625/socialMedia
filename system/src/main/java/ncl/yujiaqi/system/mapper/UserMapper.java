package ncl.yujiaqi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.system.domain.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * user table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface UserMapper extends BaseMapper<User> {

    User selectByAccountAndPwd(@Param("account") String account, @Param("pwd") String password);

    User selectByEmail(@Param("email") String email);
    User selectByPhone(@Param("phone") String phone);
}
