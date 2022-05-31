package br.com.myanalista.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import br.com.myanalista.models.entities.UsersEntity;
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
  private UserService userService;

  @Autowired
  private ModelMapper mapper;

  @GetMapping("/{term}")
  public Page<UserResponse> findAllWithList(@PageableDefault() Pageable pageable,
      @PathVariable(value = "term") String term) {
        Page<UserResponse> response = userService.getUserByTerm(term,  pageable);
    return response;
  }

  @PostMapping
  public UserResponse saveUser(@RequestBody @Valid UserRequestPost userRequestPost) {
    try {
      UserResponse resp = userService.save(userRequestPost);
      return resp;
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable(value = "id") Long id) throws Exception {
    try {
      return userService.delete(id);
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public UserResponse updateUser(@PathVariable(value = "id") Long id,
      @RequestBody @Valid UserRequestPut userRequestPut) {
    try {
      UsersEntity userEntity = new UsersEntity();
      mapper.map(userRequestPut, userEntity);
      UserResponse resp = userService.update(userRequestPut);
      UserResponse response = new UserResponse();
      mapper.map(resp, response);
      return response;
    } catch (BusinessException e) {
     throw new BusinessException(e.getMessage());
    }
  }

}
