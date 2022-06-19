package br.com.myanalista.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.models.request.LogInRequest;
import br.com.myanalista.models.response.TokenResponse;
import br.com.myanalista.security_jwt.JwtService;
import br.com.myanalista.services.LogInService;
import br.com.myanalista.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/login")
@AllArgsConstructor
public class LogingController {

  @Autowired
  private UserService serviceUser;

  @Autowired
  private LogInService serviceLogin;

  @Autowired
  private JwtService serviceJwt;

  @PostMapping
  public TokenResponse authentication(@RequestBody LogInRequest login) {
    try {
      Users user = Users.builder()
          .userEmail(login.getUserEmail())
          .password(login.getPassword())
          .build();

      UserDetails userDetails = serviceLogin.toAuthenticate(user);

      String token = serviceJwt.generateToken(user);

      return new TokenResponse(user.getUserName(), token);

    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }

  }
}
