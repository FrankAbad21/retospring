package com.frab.retospring.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Aplicacion reto Spring boot",
                description = "reto usando spring boot 3",
                termsOfService = "www.frankabad.com/termAndConditional",
                version = "1.0.0",
                contact = @Contact(
                        name = "Frank Abad",
                        url = "https://www.linkedin.com/in/frank-harrinson-abad-gaona-366005141/",
                        email = "frank.harri.2104@gmail.com"
                ),
                license = @License(
                        name = "Licencia para reto spring for Frank Abad",
                        url = "https://www.linkedin.com/in/frank-harrinson-abad-gaona-366005141"

                )
        ),
        servers = {
                @Server(
                    description = "DEV SERVER",
                    url = "http://localhost:8080/reto"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://frankabad.com"
                )
        }
)
public class SwaggerConfig {

}
