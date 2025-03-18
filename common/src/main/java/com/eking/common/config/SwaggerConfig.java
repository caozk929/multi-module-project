package com.eking.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author HHHY
 * @ClassName
 * @Date: 2024/4/2 14:21
 * @Description: Swagger 配置
 */
@Configuration
public class SwaggerConfig {

    @Value("${springdoc.info.title:}")
    private String title;
    @Value("${springdoc.info.desc:}")
    private String desc;
    @Value("${springdoc.info.version:}")
    private String version;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title(title)
                .description(desc)
                .version(version));
    }
}
