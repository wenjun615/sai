package com.wen.sai.config;

import com.wen.sai.component.JwtAuthenticationTokenFilter;
import com.wen.sai.component.RestfulAccessDeniedHandler;
import com.wen.sai.component.RestfulAuthenticationEntryPoint;
import com.wen.sai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * 安全配置
 * </p>
 *
 * @author wenjun
 * @since 2021/3/24
 */
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final IgnoreProperties ignoreProperties;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }
}
