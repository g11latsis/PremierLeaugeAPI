package gr.aueb.cf.premierAPI.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Premier API")
                        .version("1.0")
                        .description("API for Premier League")
                        .contact(new Contact()
                                .name("Grigoris Latsis")
                                .url("https://github.com/g11latsis")
                                .email("g11latsis@gmail.com")));
    }
}
