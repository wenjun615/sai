package com.wen.sai.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * MyBatis-Plus 配置
 * </p>
 *
 * @author wenjun
 * @since 2021/2/21
 */
@Configuration
@MapperScan({"com.wen.sai.mapper"})
@EnableTransactionManagement
public class MyBatisPlusConfig {
}
