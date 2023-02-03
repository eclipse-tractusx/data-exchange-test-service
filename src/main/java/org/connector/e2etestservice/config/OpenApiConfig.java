package org.connector.e2etestservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "E2E Data Exchange Test Service",
                description = "" +
                        "This service enables end to end testing of connectors",
                version = "1.0"
        ),
        servers = @Server(url = "https://e2edets.dev.demo.catena-x.net")
)
@Configuration
public class OpenApiConfig {
}
