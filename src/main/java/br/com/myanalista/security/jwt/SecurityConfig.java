package br.com.myanalista.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/v1/signin/**",
                        "/auth/refresh",
                        "/api-docs/**",
                        "/swagger-ui.html**"
                ).permitAll()
                .antMatchers(
                        "/v1/categories/**",
                        "/v1/channel/**",
                        "/v1/cluster/**",
                        "/v1/contacts/**",
                        "/v1/customer/**",
                        "/v1/distributor/**",
                        "/v1/equipment/**",
                        "/v1/lending/**",
                        "/v1/products/**",
                        "/v1/teams/**"
                ).authenticated()
                .antMatchers("/users").denyAll() // denay standard path, don't remove
                .antMatchers(HttpMethod.POST ,"/v1/user").hasRole("ADMINISTRATOR") // denay standard path, don't remove
                .and()
                .cors()
                .and()
                .apply(new JwtConfigurer(tokenProvider));
        return http.build();
    }
}
