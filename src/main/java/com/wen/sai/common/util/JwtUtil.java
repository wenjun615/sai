package com.wen.sai.common.util;

import cn.hutool.core.util.StrUtil;
import com.wen.sai.config.JwtProperties;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@AllArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    /**
     * 生成 Token
     */
    public String generateToken(UserDetails userDetails) {
        if (Objects.isNull(userDetails)) {
            return null;
        }
        Date now = new Date();
        Date expiration = generateExpiration(now);
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(jwtProperties.getUsername(), userDetails.getUsername());
        claims.put(jwtProperties.getCreateTime(), now);
        String token;
        try {
            token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                    .compact();
        } catch (Exception e) {
            log.warn("生成令牌异常：{}", e.getMessage(), e);
            token = null;
        }
        return token;
    }

    /**
     * 获取 JWT 负载（是否过期、验签）
     */
    public Claims findClaims(String token) {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("令牌校验异常：{}", e.getMessage(), e);
            claims = null;
        }
        return claims;
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
