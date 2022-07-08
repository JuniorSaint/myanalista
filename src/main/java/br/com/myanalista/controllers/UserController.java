package br.com.myanalista.controllers;

import br.com.myanalista.models.request.ChangePasswordRequest;
import br.com.myanalista.models.request.LogInRequest;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/users")
@AllArgsConstructor

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAllPageable(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequestPost userRequestPost) {
        try {
            return service.save(userRequestPost);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable(value = "id") Long id,
                                               @RequestBody UserRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<Object> changePassowrd(@PathVariable(value = "id") Long id,
                                                 @RequestBody ChangePasswordRequest request) {
        try {
            return service.changePassword(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/validate-password")
    public ResponseEntity<Boolean> validatePassword(@RequestBody LogInRequest logInRequest) {
        try {
            return service.validatePassword(logInRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
