package br.com.mtonon.parkingapi.config;

/**
 * Classe de configuração do timezone da aplicação. Utilizada para setar na aplicação,
 * o timezone referente à data e hora que o java deverá utilizar para a aplicação. No
 * nosso caso, foi setado o timezone do Brasil
 */

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SpringTimezoneConfig {
	
	@PostConstruct
	public void timezoneConfig() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
