package com.wen.sai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Redis 配置
 * </p>
 *
 * @author wenjun
 * @since 2021-06-27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private String database;

    private Key key;

    private Long expire;

    @Data
    public static class Key {

        private String user;

        private String resources;
    }
}
