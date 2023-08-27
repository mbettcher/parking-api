package br.com.mtonon.parkingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    OpenAPI openAPI() {
		return new OpenAPI()
				.info(
						new Info()
							.title("REST API - Spring Parking")
							.description("Api para gerenciamento de Vagas de Estacionamento de Veículos")
							.version("v1")
							.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
							.contact(new Contact().name("Marcelo Tonon Bettcher").email("mtonon.pmg@gmail.com"))
					);
	}

}
