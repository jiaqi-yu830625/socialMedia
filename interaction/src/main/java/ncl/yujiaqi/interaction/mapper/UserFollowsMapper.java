package ncl.yujiaqi.interaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ncl.yujiaqi.interaction.domain.entity.UserFollows;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * user follows table Mapper 接口
 * </p>
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface UserFollowsMapper extends BaseMapper<UserFollows> {

    UserFollows selectByUserAndFollowUser(@Param("userId") Long userId, @Param("followUserId") Long followUserId);

    List<UserFollows> selectByUserId(@Param("userId") Long userId);
}
