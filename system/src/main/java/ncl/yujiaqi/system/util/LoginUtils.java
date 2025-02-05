package ncl.yujiaqi.system.util;

import ncl.yujiaqi.system.common.enums.ResultEnum;
import ncl.yujiaqi.system.common.exception.SMException;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @Author yujiaqi
 * @Since 05/02/2025
 */
public class LoginUtils {

    public static Object getPrincipal() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .orElseThrow(() -> SMException.build(ResultEnum.USER_NOT_FOUND));
    }

    public static UserDTO getUserDTO() {
        return Optional.of(getPrincipal())
                .filter(principal -> principal instanceof UserDTO)
                .map(principal -> (UserDTO) principal)
                .orElseThrow(() -> SMException.build(ResultEnum.USER_NOT_FOUND));
    }
}
