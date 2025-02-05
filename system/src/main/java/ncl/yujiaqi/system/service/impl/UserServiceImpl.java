package ncl.yujiaqi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.domain.dto.LoginDTO;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.domain.dto.UserTokenDTO;
import ncl.yujiaqi.system.domain.entity.Role;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.domain.entity.UserRole;
import ncl.yujiaqi.system.mapper.UserMapper;
import ncl.yujiaqi.system.service.RoleService;
import ncl.yujiaqi.system.service.UserRoleService;
import ncl.yujiaqi.system.service.UserService;
import ncl.yujiaqi.system.util.JwtTokenUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * user table ServiceImpl
 *
 * @author yujiaqi
 * @since 2025-02-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User add(User user) {
        // check duplicate (email and phone)
        List<User> users = selectByAccount(user.getEmail(), user.getPhone());
        if (!users.isEmpty()) {
            throw SMException.build(ResultEnum.DATA_REPEAT, "email or phone is exist");
        }
        // user's password should be encoded
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        baseMapper.insert(user);
        // user's role default 'user', as admin cannot register
        Role role = roleService.getByName("user");
        userRoleService.add(new UserRole(user.getId(), role.getId()));

        return user;
    }

    @Override
    public User update(User user) {
        User originUser = getById(user.getId());
        originUser.setUsername(user.getUsername())
                .setBio(user.getBio())
                .setAvatar(user.getAvatar())
                .setGender(user.getGender())
                .setBirthday(user.getBirthday())
                .setUpdateTime(new Date());
        baseMapper.updateById(originUser);

        originUser.setPassword(null);
        return originUser;
    }

    @Override
    public Boolean delete(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public UserTokenDTO login(LoginDTO loginDTO) {
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        if (account == null || "".equals(account)) {
            throw SMException.build(ResultEnum.LOGIN_PWD_WRONG, "account cannot be null");
        }
        if (password == null || "".equals(password)) {
            throw SMException.build(ResultEnum.LOGIN_PWD_WRONG, "password cannot be null");
        }

        // check account and password in database
        User user = userMapper.selectByAccountAndPwd(account, password);
        if (Objects.isNull(user)) {
            throw SMException.build(ResultEnum.LOGIN_PWD_WRONG);
        }
        //if user exist generate token
        UserDTO userDTO = convert(user);
        String token = JwtTokenUtils.genToken(userDTO);
        return new UserTokenDTO(user, token);
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        //todo
        return false;
    }

    @Override
    public User getCurrentUser() {
        //todo
        return null;
    }

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        userDTO.setRoles(userRoleService.selectByUserId(user.getId()));
        return userDTO;
    }

    public List<User> selectByAccount(String email, String phone) {
        return baseMapper.selectByAccount(email, phone);
    }

}
