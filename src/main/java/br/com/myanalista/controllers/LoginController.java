//package br.com.myanalista.controllers;
//
//import br.com.myanalista.models.entities.Users;
//import br.com.myanalista.models.request.LogInRequest;
//import br.com.myanalista.models.response.TokenResponse;
//import br.com.myanalista.security.service.UserDetailsServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/v1/auth")
//@AllArgsConstructor
//public class LoginController {
//
//    @Autowired
//    private UserDetailsServiceImpl userService;
//
//    @PostMapping
//    public TokenResponse authenticate(@RequestBody LogInRequest credencial) {
//      Users users = Users.builder()
//              .userEmail(credencial.getUserEmail())
//              .password(credencial.getPassword())
//              .build();
//          UserDetails userDetails = userService.authenticate(users);
//    }
//}
