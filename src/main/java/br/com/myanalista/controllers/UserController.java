package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Categories;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAllUserPageable(Pageable page) {
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

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Optional<Long> id) {
        try {
            return service.delete(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody UserRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> changePassowrd(@RequestBody ChangePasswordRequest request) {
        try {
            return service.changePassword(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> findAllUserWithSearch(@RequestParam Optional<String> search, Pageable pageable) {
        try {
            if (search.isEmpty()) {
                return service.findAllWithPage(pageable);
            }
            return service.findAllWithPageSeek(search.get(), pageable);
        } catch (NoSuchElementException e) {
            return service.findAllWithPage(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
