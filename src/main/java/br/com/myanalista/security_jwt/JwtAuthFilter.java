package br.com.myanalista.security_jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.myanalista.services.UserService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private UserService userService;

  public JwtAuthFilter(JwtService jwtService, UserService userService) {
    this.jwtService = jwtService;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authorization = request.getHeader("Authorization");

    if (authorization != null && authorization.startsWith("Bearer")) {
      String token = authorization.split(" ")[1];
      boolean isValid = jwtService.validToken(token);

      if (isValid) {
        String loginUser = jwtService.getUserLogged(token);
        UserDetails userDetails = userService.loadUserByUsername(loginUser);
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(user);
      }
    }
    filterChain.doFilter(request, response);
  }

}
