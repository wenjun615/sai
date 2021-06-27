package com.wen.sai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author wenjun
 * @since 2021-06-28
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String authHeader;

    private String tokenHead;

    private String secret;

    private Long expiration;

    private String username;

    private String createTime;
}
