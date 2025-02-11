package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.interaction.domain.dto.UserFollowDTO;
import ncl.yujiaqi.interaction.domain.entity.UserFollows;
import ncl.yujiaqi.interaction.service.UserFollowsService;
import ncl.yujiaqi.system.common.result.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * user follows table controller
 *
 * @author yujiaqi
 * @since 2025-02-05
 */
@RestController
@Api(value = "user follows table", tags = "user follows table")
@RequestMapping("/follows")
public class UserFollowsController {

    @Resource
    private UserFollowsService userFollowsService;

    @PostMapping(value = "/follow/{followUserId}")
    @ApiOperation(tags = "follow", value = "follow user")
    public R<UserFollows> add(@PathVariable("followUserId") Long followUserId) {
        return R.success(userFollowsService.addById(followUserId));
    }

    @DeleteMapping(value = "/follow/{followUserId}")
    @ApiOperation(tags = "unfollow", value = "unfollow user")
    public R cancel(@PathVariable("followUserId") Long followUserId) {
        userFollowsService.cancelById(followUserId);
        return R.success();
    }

    @GetMapping(value = "/getFollows/{userId}")
    @ApiOperation(tags = "getFollowsByUserId", value = "getFollowsByUserId")
    public R<UserFollowDTO> getFollowsByUserId(@PathVariable("userId") Long userId){
        return R.success(userFollowsService.getFollowsByUserId(userId));
    }

}
