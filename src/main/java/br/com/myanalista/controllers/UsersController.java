package br.com.myanalista.controllers;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/users")
@AllArgsConstructor

public class UsersController {

  @Autowired
  private UserService service;

  @GetMapping("/term/{term}/{page}")
  public Page<UserResponse> findAllWithList(@PathVariable(value = "page") Integer page,
      @PathVariable(value = "term") Users users) {
        Page<UserResponse> response = service.getUserByTerm(users,  page);
    return response;
  }

  @GetMapping("/{id}")
  public UserResponse findById(@PathVariable(value = "id") Long id) {
    UserResponse response = service.findById(id);
    return response;
  }

  @PostMapping
  public UserResponse save(@RequestBody @Valid UserRequestPost userRequestPost) {
    try {
      UserResponse resp = service.save(userRequestPost);
      return resp;
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable(value = "id") Long id) {
    try {
      return service.delete(id);
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public UserResponse update(@PathVariable(value = "id") Long id,
                             @RequestBody UserRequestPut request) {
    try {
      UserResponse response = service.update(request);
      return response;
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

}
