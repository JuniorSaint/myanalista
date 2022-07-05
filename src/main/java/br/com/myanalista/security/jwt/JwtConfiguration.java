package br.com.myanalista.security.jwt;

import br.com.myanalista.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JwtConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    private static final String[] PUBLIC_MATCHES = {"/", "/**", "/auth/**", "/swagger-ui.html", "/webjars/**",
            "/v2/api-docs"};
    private static final String[] PUBLIC_MATCHES_AUTHENTICATE = {"/**"};

    public JwtConfiguration(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(PUBLIC_MATCHES).permitAll()
                .antMatchers(PUBLIC_MATCHES_AUTHENTICATE).permitAll()
                .antMatchers("/v1/users/**").permitAll()
                .antMatchers("/v1/chargeunique/**").permitAll()
                .antMatchers("/v1/teams/**").permitAll()
                .antMatchers("/v1/categories/**").permitAll()
                .antMatchers("/v1/teams/**").permitAll()
                .antMatchers("/v1/products/**").permitAll()
                .antMatchers("/v1/contacts/**").permitAll()
                .antMatchers("/v1/distributor/**").permitAll()
                .antMatchers("/v1/lending/**").permitAll()
                .antMatchers("/v1/login/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(new AuthTokenFilter(authenticationManager()))
                .addFilter(new JwtValidFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}