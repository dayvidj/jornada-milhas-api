package com.jornadamilhas.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${api.server-url}")
	private String serverUrl;

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Jornada Milhas - API")
						.version("1.0")
						.description("API REST para gerenciamento de recursos."))
				.servers(List.of(new Server()
						.url(serverUrl)
						.description("Servidor principal")));
	}

}
