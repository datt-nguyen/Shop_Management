package com.example.management_shop.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shop Management API")
                        .description("API Document for ShopManagement project (Spring Boot 3 + SpringDoc OpenAPI).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Đạt Nguyễn")
                                .email("dat@example.com")
                                .url("https://github.com/example"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
