package br.com.myanalista.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class GeralConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper;
  }
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("API to manager application Myanalist")
				.version("v1")
				.description("RESTful API develop with Java 11 and Spring Boot 2.7")
				.termsOfService("https://www.idipi.com.br")
				.license(
					new License()
						.name("Apache 2.0")
						.url("https://www.idipi.com.br")
					)
				);
	}

}
