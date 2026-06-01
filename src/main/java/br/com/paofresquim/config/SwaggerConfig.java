package br.com.paofresquim.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Padaria Pão Fresquim API")
                        .description("API para gerenciamento da padaria")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Pão Fresquim")
                                .email("louquim@paofresquim.com.br")));
    }
}