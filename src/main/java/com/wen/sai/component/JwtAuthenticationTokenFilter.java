package com.wen.sai.component;

import cn.hutool.core.util.StrUtil;
import com.wen.sai.common.util.JwtUtil;
import com.wen.sai.config.JwtProperties;
import com.wen.sai.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * JWT 登录授权过滤器
 * </p>
 *
 * @author wenjun
 * @since 2020/12/22
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        JwtUtil jwtUtil = new JwtUtil(jwtProperties);
        String authHeader = request.getHeader(jwtProperties.getAuthHeader());
        if (StrUtil.isNotBlank(authHeader) && authHeader.startsWith(jwtProperties.getTokenHead())) {
            String token = authHeader.substring(jwtProperties.getTokenHead().length());
            Claims claims = jwtUtil.findClaims(token);
            if (Objects.nonNull(claims)) {
                String username = String.valueOf(claims.get(jwtProperties.getUsername()));
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (Objects.nonNull(userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("用户 {} 已登录", username);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
