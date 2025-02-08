package ncl.yujiaqi.interaction.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.interaction.domain.entity.UserFollows;
import ncl.yujiaqi.interaction.service.UserFollowsService;
import ncl.yujiaqi.system.common.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/follow")
    @ApiOperation(tags="follow" ,value = "follow user")
    public R<UserFollows> add(Long followUserId){
        return R.success(userFollowsService.addById(followUserId));
    }
}
