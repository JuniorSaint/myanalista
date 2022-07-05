package br.com.myanalista.security.jwt;

import br.com.myanalista.configs.CP;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.security.service.DetailDataUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthTokenFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Users usuario = new ObjectMapper()
                    .readValue(request.getInputStream(), Users.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getUserEmail(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Fail to authenticate user", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetailDataUser userData = (DetailDataUser) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(userData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + CP.EXPIRATION))
                .sign(Algorithm.HMAC512(CP.SIGNATURE_KEY));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}