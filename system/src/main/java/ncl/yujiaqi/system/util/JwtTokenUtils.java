package ncl.yujiaqi.system.util;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.domain.entity.User;
import ncl.yujiaqi.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author yujiaqi
 * @Since 04/02/2025
 */
@Component
public class JwtTokenUtils {
    private static UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);
    private static final String SECRET_KEY = "yujiaqi-api";

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    // generate token
    public static String genToken(UserDTO user) {
        Map<String, Object> claim = new HashMap<>(2);
        claim.put("USERID", user.getId());
        claim.put("EMAIL", user.getEmail());
        claim.put("PHONE", user.getPhone());
        claim.put("userDTO", user);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(DateUtil.offsetHour(new Date(), 2))
                .signWith(SignatureAlgorithm.HS256, generateKey());
        return jwtBuilder.compact();
    }

    private static SecretKey generateKey() {
        byte[] encodedKey = Base64.getEncoder().encode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * decode token and get user
     *
     * @param token
     * @return
     */
    public static User parseToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token)
                    .getBody();
            Long userId = (long) claims.get("USERID");
            return staticUserService.getById(userId);
        } catch (Exception e) {
            return null;
        }
    }
}
