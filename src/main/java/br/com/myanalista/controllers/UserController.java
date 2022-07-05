package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Users;
import br.com.myanalista.models.request.ChangePasswordRequest;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/users")
@AllArgsConstructor

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/term/{term}/{page}")
    public Page<UserResponse> findAllWithList(@PathVariable(value = "page") Integer page,
                                              @PathVariable(value = "term") Users users) {
        return service.getUserByTerm(users, page);
    }

    @GetMapping("/page")
    public Page<UserResponse> findAllWithList(Pageable page) {
        return service.findAllWithPage(page);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserResponse save(@RequestBody @Valid UserRequestPost userRequestPost) {
            return service.save(userRequestPost);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
            return service.delete(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable(value = "id") Long id,
                               @RequestBody UserRequestPut request) {
            return service.update(request);
    }

    @PutMapping("/change-password/{id}")
    public String changePassowrd(@PathVariable(value = "id") Long id,
                                 @RequestBody ChangePasswordRequest request) {
            return service.changePassword(request);
    }
}
