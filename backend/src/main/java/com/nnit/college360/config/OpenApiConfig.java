package com.nnit.college360.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI college360OpenAPI() {
        return new OpenAPI().info(new Info()
                .title("NNIT College360 API")
                .version("1.0.0")
                .description("College management platform REST API")
                .contact(new Contact().name("NNIT")));
    }
}
