package br.com.myanalista.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.myanalista.services.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService serviceUser;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceUser).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/categories/**").hasAnyRole("ADMINISTRATOR", "COLLABORATOR")
                .antMatchers("/v1/contacts/**").hasAnyRole("ADMINISTRATOR", "COLLABORATOR")
                .antMatchers("/v1/customer/**").hasAnyRole("ADMINISTRATOR", "COLLABORATOR")
                .antMatchers("/v1/teams/**").authenticated()
                .antMatchers("/v1/login").permitAll()
                // .antMatchers("/v1/users/**").hasAnyRole("ADMINISTRATOR")
                .and();
    }
}
