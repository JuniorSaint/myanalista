package br.com.myanalista.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.request.LogInRequest;
import br.com.myanalista.models.response.TokenResponse;
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
    private LogInService logInService;

    @PostMapping
    public ResponseEntity<TokenResponse> authenticateUser(
            @Valid @RequestBody LogInRequest loginRequest) {
        return ResponseEntity
                .ok(logInService.signin(loginRequest.getUserEmail(), loginRequest.getPassword()));
    }
}