package com.example.shopping.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String accessTokenHeader = "access"; // 헤더 이름 설정
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(accessTokenHeader);
        Components components = new Components().addSecuritySchemes(accessTokenHeader, new SecurityScheme()
                .name(accessTokenHeader)
                .type(SecurityScheme.Type.APIKEY) // API 키 형식
                .in(SecurityScheme.In.HEADER) // 헤더에서 추출
        );

        return new OpenAPI()
                .components(components)
                .info(apiInfo())
                .addSecurityItem(securityRequirement);
    }

    private Info apiInfo() {
        return new Info()
                .title("API Test") // API의 제목
                .description("Let's practice Swagger UI") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
