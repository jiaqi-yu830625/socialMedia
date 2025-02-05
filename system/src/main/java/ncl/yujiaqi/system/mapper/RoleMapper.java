package ncl.yujiaqi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.system.domain.entity.Role;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * role table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
public interface RoleMapper extends BaseMapper<Role> {
    Role getByName(@Param("name") String name);

}
