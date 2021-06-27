package com.wen.sai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 白名单配置
 * </p>
 *
 * @author wenjun
 * @since 2021/3/24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreProperties {

    private List<String> urls = new ArrayList<>();
}
