package br.com.myanalista.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/users")
@AllArgsConstructor

public class UsersController {

  @Autowired
  private UserService service;

  @GetMapping("/term/{term}")
  public Page<UserResponse> findAllWithList(@PageableDefault() Pageable pageable,
      @PathVariable(value = "term") String term) {
        Page<UserResponse> response = service.getUserByTerm(term,  pageable);
    return response;
  }

  @GetMapping("/{id}")
  public UserResponse findAllWithListTeams(@PathVariable(value = "id") Long id) {
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
