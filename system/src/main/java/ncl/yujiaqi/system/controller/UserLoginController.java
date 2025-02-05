package ncl.yujiaqi.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.system.common.result.R;
import ncl.yujiaqi.system.domain.dto.LoginDTO;
import ncl.yujiaqi.system.domain.dto.UserTokenDTO;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yujiaqi
 * @Since 04/02/2025
 */
@RestController
@Api(value = "user table", tags = "user table")
@RequestMapping("/login")
public class UserLoginController {
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(tags = "login", value = "login")
    public R login(@RequestBody LoginDTO loginDTO) {
        UserTokenDTO userTokenDTO = userService.login(loginDTO);
        return R.success(userTokenDTO);
    }

    @GetMapping(value = "/getCurrentUser")
    @ApiOperation(value = "get current user")
    public R<User> getCurrentUser() {
        return R.success(userService.getCurrentUser());
    }
}
