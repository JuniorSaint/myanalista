package br.com.myanalista.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Users;

@Service
public class LogInService {

  @Autowired
  UserService serviceUser;

  @Autowired
  private PasswordEncoder encoder;

  public UserDetails toAuthenticate(Users user) {

    UserDetails userDetails = serviceUser.loadUserByUsername(user.getUserEmail());
    boolean isMatch = encoder.matches(user.getPassword(), userDetails.getPassword());
    if (isMatch) {
      return userDetails;
    }
    throw new BusinessException("Email: " + user.getUserEmail() + ", or password don't match.");
  }

}
