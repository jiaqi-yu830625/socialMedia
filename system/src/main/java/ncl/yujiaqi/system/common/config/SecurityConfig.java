package ncl.yujiaqi.system.common.config;

import com.alibaba.fastjson.JSON;
import ncl.yujiaqi.system.common.filter.JwtAuthenticationFilter;
import ncl.yujiaqi.system.common.result.R;
import ncl.yujiaqi.system.domain.dto.UserDTO;
import ncl.yujiaqi.system.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static ncl.yujiaqi.system.common.enums.ResultEnum.*;

/**
 * @Author yujiaqi
 * @Since 04/02/2025
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/login/login").permitAll() // 允许登录接口
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 只需要登录就能访问的请求
                .antMatchers("/user/register", "/login/login", "/api/login/login").permitAll()
//                .antMatchers("/login/login").access("authenticated")
                // 剩余所有请求都要经过认证且判断权限
                .anyRequest().access("authenticated and @resourceCertification.access(request)")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, LogoutFilter.class)
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> respond(response, JSON.toJSONString(R.fail(LOGIN_VERIFY_FAIL)));
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> respond(response, JSON.toJSONString(R.fail(AUTH_FAILED)));
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            respond(response, JSON.toJSONString(getToken(authentication)));
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, authentication) -> respond(response, JSON.toJSONString(R.fail(LOGIN_PWD_WRONG)));
    }

    private R<String> getToken(Authentication authentication) {
        return Optional.ofNullable(authentication.getPrincipal())
                .map(principal -> (UserDTO) principal)
                .map(JwtTokenUtils::genToken)
                .map(R::success)
                .orElseGet(() -> R.fail(USER_NOT_FOUND));
    }

    private void respond(HttpServletResponse response, String result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result);
    }
}