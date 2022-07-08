package br.com.myanalista;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"br.com.myanalista.repositories"})
@EntityScan(basePackages = {"br.com.myanalista.models.entities"})
@ComponentScan(basePackages = {"br.com.myanalista.controllers",
        "br.com.myanalista.services", "br.com.myanalista.configs",
        "br.com.myanalista.security.jwt", "br.com.myanalista.security.service",
        "br.com.myanalista.security.service", "br.com.myanalista.exceptions"
})

public class MyanalistaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyanalistaApplication.class, args);
    }
}

