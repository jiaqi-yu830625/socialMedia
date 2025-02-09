package ncl.yujiaqi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.system.domain.entity.Role;
import ncl.yujiaqi.system.domain.entity.UserRole;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * user role table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> selectByUserId(@Param("userId") Long userId);
}
