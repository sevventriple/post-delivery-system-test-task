package com.example.postdeliverysystemtesttask.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI postDeliveryTrackingSystem() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Сервис для отслеживания почтовых отправлений")
                                .description("Сервис для отслеживания почтовых отправлений")
                                .contact(
                                        new Contact()
                                                .email("alexkost631@gmail.com")
                                                .name("Кострыгин Алексей")
                                )
                );
    }
}
