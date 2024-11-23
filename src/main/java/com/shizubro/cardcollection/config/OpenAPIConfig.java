package com.shizubro.cardcollection.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Michelle Liu");
        myContact.setEmail("");

        Info information = new Info()
                .title("Card Collection Management API")
                .version("1.0")
                .description("This API exposes endpoints to access card data and keep track of lent and borrowed cards in a Discord server")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}