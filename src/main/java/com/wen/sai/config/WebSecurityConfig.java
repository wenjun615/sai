package com.wen.sai.config;

import com.wen.sai.component.*;
import com.wen.sai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 * 安全配置
 * </p>
 *
 * @author wenjun
 * @since 2021/3/24
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final IgnoreProperties ignoreProperties;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    private final DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    private final DynamicAccessDecisionManager dynamicAccessDecisionManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected UserDetailsService userDetailsService() {
        return userService::loadUserByUsername;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.authorizeRequests();
        ignoreProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(dynamicSecurityMetadataSource);
                        o.setAccessDecisionManager(dynamicAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf()
                .disable();
    }
}
