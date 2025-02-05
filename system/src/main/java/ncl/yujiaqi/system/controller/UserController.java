package ncl.yujiaqi.system.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.common.result.R;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * user table 前端控制器
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@RestController
@Api(value = "user table", tags = "user table")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    @ApiOperation(tags = "create user", value = "create user info")
    public R<User> addUser(@Validated @RequestBody User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return R.fail(result);
//        }
        // email or phone must have one to be the account
        if (StrUtil.isBlank(user.getEmail()) && StrUtil.isBlank(user.getPhone())) {
            throw SMException.build(ResultEnum.PARAM_NOT_FOUND, "account cannot empty!!!");
        }
        return R.success(userService.add(user));
    }

    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "update password")
    public R updatePassword(@Param("id") Long id, @Param("password") String password) {
        userService.updatePassword(id, password);
        return R.success();
    }

    @PostMapping(value = "/updateInfo")
    @ApiOperation(value = "update info")
    public R<User> updateInfo(@RequestBody User user) {
        return R.success(userService.update(user));
    }

}
