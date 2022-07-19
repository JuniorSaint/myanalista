package br.com.myanalista.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.request.LogInRequest;
import br.com.myanalista.security.jwt.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1")
@Tag(name = "Auth", description = "To obtain the jwt")
public class AuthController {

    @Autowired
    AuthService authServices;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody LogInRequest data) {
        if (checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

//    @SuppressWarnings("rawtypes")
//    @Operation(summary = "Refresh token for authenticated user and returns a token")
//    @PutMapping(value = "/refresh/{username}")
//    public ResponseEntity refreshToken(@PathVariable("username") String username,
//                                       @RequestHeader("Authorization") String refreshToken) {
//        if (checkIfParamsIsNotNull(username, refreshToken))
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//        var token = authServices.refreshToken(username, refreshToken);
//        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//        return token;
//    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    private boolean checkIfParamsIsNotNull(LogInRequest data) {
        return data == null || data.getEmail() == null || data.getEmail().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
    }
}
