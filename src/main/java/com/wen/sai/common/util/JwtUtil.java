package com.wen.sai.common.util;

import cn.hutool.core.util.StrUtil;
import com.wen.sai.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JWT 工具类
 *
 * @author wenjun
 * @since 2021-06-28
 */
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成 Token
     */
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiration = generateExpiration(now);
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(jwtProperties.getUsername(), userDetails.getUsername());
        claims.put(jwtProperties.getCreateTime(), now);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 获取 Token 登录用户名
     */
    public String findUsername(String token) {
        Claims claims = findClaims(token);
        return Objects.isNull(claims) ? null : claims.getSubject();
    }

    /**
     * 刷新 Token
     */
    public String refreshToken(String oldToken) {
        if (StrUtil.isBlank(oldToken)) {
            return null;
        }
        Claims claims = findClaims(oldToken);
        if (Objects.isNull(claims) || isExpired(claims)) {
            return null;
        }
        Date now = new Date();
        Date expiration = generateExpiration(now);
        claims.put(jwtProperties.getCreateTime(), now);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * 生成 Token 过期时间
     */
    private Date generateExpiration(Date date) {
        return new Date(date.getTime() + jwtProperties.getExpiration() * 1000);
    }

    /**
     * 获取 JWT 负载
     */
    private Claims findClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Token 是否过期
     */
    private boolean isExpired(String token) {
        Claims claims = findClaims(token);
        if (Objects.nonNull(claims)) {
            return new Date().after(claims.getExpiration());
        }
        return true;
    }

    /**
     * Token 是否过期
     */
    private boolean isExpired(Claims claims) {
        if (Objects.nonNull(claims)) {
            return new Date().after(claims.getExpiration());
        }
        return true;
    }
}
