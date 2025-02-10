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
import ncl.yujiaqi.system.util.LoginUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
        User checkEmail = selectByAccount(user.getEmail());
        User checkPhone = selectByAccount(user.getPhone());
        if (checkEmail != null || checkPhone != null) {
            throw SMException.build(ResultEnum.DATA_REPEAT, "email or phone is exist");
        }
        // user's password should be encoded
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setIsActive(true);
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

        // check user exist
        User byEmail = userMapper.selectByEmail(account);
        User byPhone = userMapper.selectByPhone(account);
        User user = byEmail != null ? byEmail : byPhone;
        if (Objects.isNull(user)) {
            throw SMException.build(ResultEnum.USER_NOT_FOUND, "wrong account");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw SMException.build(ResultEnum.LOGIN_PWD_WRONG, "wrong password");
        }

        //if user exist generate token
        UserDTO userDTO = convert(user);
        String token = JwtTokenUtils.genToken(userDTO);
        return new UserTokenDTO(user, token);
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        User user = getById(id);
        if (user == null) {
            throw SMException.build(ResultEnum.DATA_NOT_FOUND, "user not exist!");
        }
        user.setPassword(passwordEncoder.encode(password));
        return save(user);
    }

    /**
     * get user by token
     *
     * @return user
     */
    @Override
    public UserDTO getCurrentUser() {
        return LoginUtils.getUserDTO();
    }

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user, userDTO);
        userDTO.setRoles(userRoleService.selectByUserId(user.getId()));
        return userDTO;
    }

    @Override
    public User getUserById(Long id) {
        User user = getById(id);
        if (user == null) {
            throw SMException.build(ResultEnum.DATA_NOT_FOUND, "user not exist!");
        }
        user.setPassword(null);
        return user;
    }

    public User selectByAccount(String account) {
        User byEmail = userMapper.selectByEmail(account);
        User byPhone = userMapper.selectByEmail(account);
        return byEmail != null ? byEmail : byPhone;
    }

}
