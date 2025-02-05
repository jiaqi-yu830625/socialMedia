package ncl.yujiaqi.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ncl.yujiaqi.dynamic.domain.entity.UserFollows;

/**
 * user follows table
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
public interface UserFollowsService extends IService<UserFollows> {


    UserFollows add(UserFollows userFollows);

    UserFollows update(UserFollows userFollows);

    Boolean delete(Long id);
}
