package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.myanalista.exceptions.ErrorToConnect;
import br.com.myanalista.models.entities.UsersEntity;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.repositories.UserRepository;

public class UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Transactional
  public UserResponse save(UserRequestPost userRequest) {
    try {
      Optional<UserResponse> searchForUser = getUserByEmail(userRequest.getUserEmail());
      if (!searchForUser.isEmpty()) {
        throw new ErrorToConnect(
            "Already exist user with this email: " + userRequest.getUserEmail() + ", try with another one");
      }
      UsersEntity userEntity = new UsersEntity();
      mapper.map(userRequest, userEntity);
      UsersEntity userCreated = repository.save(userEntity);
      UserResponse userResponse = new UserResponse();
      mapper.map(userCreated, userResponse);
      return userResponse;
    } catch (ErrorToConnect e) {
      throw new ErrorToConnect("It's not possible save user");
    }
  }

  @Transactional
  public UserResponse update(UserRequestPut userRequestPut) {
    try {
      Optional<UsersEntity> user = repository.findById(userRequestPut.getId());
      if (!user.isPresent()) {
        throw new ErrorToConnect("User not found with id: " + userRequestPut.getId());
      }
      UsersEntity userEntity = new UsersEntity();
      mapper.map(userRequestPut, userEntity);
      UsersEntity userUpdate = repository.save(userEntity);
      UserResponse userResponse = new UserResponse();
      mapper.map(userUpdate, userResponse);
      return userResponse;
    } catch (ErrorToConnect e) {
      throw new ErrorToConnect("It's not possible update user");
    }
  }

  @Transactional
  public void delete(Long id) throws Exception {
    try {
      Optional<UsersEntity> user = repository.findById(id);
      if (!user.isPresent()) {
        throw new ErrorToConnect("user not found with id: " + id);
      }
      repository.deleteById(id);
    } catch (ErrorToConnect e) {
      throw new ErrorToConnect("Error to delete user with id: " + id);
    }
  }

  public Optional<UserResponse> getUserByEmail(String email) {
    Optional<UserResponse> resp = repository.findByUserEmail(email);
    if (resp.isEmpty()) {
      throw new ErrorToConnect("There isn't user with this email: " + email);
    }
    return resp;
  }

  public Page<UserResponse> getUserByTerm(String term, Pageable pageable) {
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase();
    Example<String> example = Example.of(term, matcher);
    Page<UsersEntity> respFromRepository = repository.findAll(example, pageable);
    Page<UserResponse> response = mapEntityPageIntoDtoPage(respFromRepository, UserResponse.class);    
    return response;
  }

  // Generic convertion Page<Entity> to Page<Dto>
  public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
  }

}
