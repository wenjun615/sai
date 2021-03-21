package com.wen.sai.config;

import com.wen.sai.entity.SwaggerApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Swagger 配置
 * </p>
 *
 * @author wenjun
 * @since 2021/1/25
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建 API 应用
     */
    @Bean
    public Docket createRestApi() {
        SwaggerApiInfo swaggerApiInfo = SwaggerApiInfo.builder()
                .basePackage("com.wen.sai")
                .title("Sai API 文档")
                .description("Sai API 文档")
                .version("1.0.0")
                .contactName("wenjun")
                .contactUrl("https://github.com/wenjun615")
                .contactEmail("835045686@qq.com")
                .build();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swaggerApiInfo))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerApiInfo.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * API 文档基本信息
     *
     * @param swaggerApiInfo Swagger API 文档信息
     * @return API 文档基本信息
     */
    private ApiInfo apiInfo(SwaggerApiInfo swaggerApiInfo) {
        return new ApiInfoBuilder()
                .title(swaggerApiInfo.getTitle())
                .description(swaggerApiInfo.getDescription())
                .version(swaggerApiInfo.getVersion())
                .contact(new Contact(
                        swaggerApiInfo.getContactName(),
                        swaggerApiInfo.getContactUrl(),
                        swaggerApiInfo.getContactEmail()))
                .build();
    }

    /**
     * 授权配置
     */
    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey(
                "Authorization",
                "token",
                io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString()));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "授权信息全局应用")};
        SecurityReference reference = new SecurityReference("Authorization", scopes);
        SecurityContext context = SecurityContext.builder()
                .securityReferences(Collections.singletonList(reference))
                .build();
        return Collections.singletonList(context);
    }
}
