package ncl.yujiaqi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.system.domain.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<User> selectByAccount(@Param("email") String email, @Param("phone") String phone);
}
