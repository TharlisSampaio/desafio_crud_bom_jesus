package com.desafiobomjesus.bom_jesus_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig  {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Bom Jesus")
                        .description("API for stundent and teacher registrations, with address")
                        .version("1.0")
                        .contact(new Contact().name("tharlis.sampaio@outlook.com"))
            );
    }
}
