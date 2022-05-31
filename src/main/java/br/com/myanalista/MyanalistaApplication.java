package br.com.myanalista;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = { "br.com.myanalista.repositories" })
@EntityScan(basePackages = { "br.com.myanalista.models.entities" })
public class MyanalistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyanalistaApplication.class, args);
	}

}
