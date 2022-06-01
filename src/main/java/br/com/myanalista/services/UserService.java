package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.UsersEntity;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private ModelMapper mapper;

  UserResponse userResponse = new UserResponse();

  @Transactional
  public UserResponse save(UserRequestPost userRequest) {
    Optional<UsersEntity> searchForUser = repository.findByUserEmail(userRequest.getUserEmail());
    if (searchForUser.isPresent()) {
      throw new BusinessException(
          "Already exist user with this email: " + userRequest.getUserEmail() + ", try with another one");
    }
    UsersEntity userEntity = new UsersEntity();
    mapper.map(userRequest, userEntity);
    UsersEntity userCreated = repository.save(userEntity);
    return convertEntityToUserResponse(userCreated);
  }

  @Transactional
  public UserResponse update(UserRequestPut userRequestPut) {
    Optional<UsersEntity> user = repository.findById(userRequestPut.getId());
    if (!user.isPresent()) {
      throw new BusinessException("User not found with id: " + userRequestPut.getId());
    }
    UsersEntity userEntity = new UsersEntity();
    mapper.map(userRequestPut, userEntity);
    UsersEntity userUpdate = repository.save(userEntity);
    return convertEntityToUserResponse(userUpdate);
  }

  @Transactional
  public String delete(Long id) {
    Optional<UsersEntity> user = repository.findById(id);
    if (!user.isPresent()) {
      throw new BusinessException("user not found with id: " + id);
    }
    repository.deleteById(id);
    return "User deleted with success";
  }

  public UserResponse findById(Long id) {
    Optional<UsersEntity> response = repository.findById(id);
    if (response.isEmpty()) {
      throw new BusinessException("There isn't user with this id: " + id);
    }
    // mapper.map(response, userResponse);
    BeanUtils.copyProperties(response.get(), userResponse);

    return userResponse;
  }

  public Page<UserResponse> getUserByTerm(String term, Pageable pageable) {
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase();
    Example<String> example = Example.of(term, matcher);
    Page<UsersEntity> respFromRepository = repository.findAll(example, pageable);
    Page<UserResponse> response = mapEntityPageIntoDtoPage(respFromRepository, UserResponse.class);
    return response;
  }

  // Convert entity to Dto
  private UserResponse convertEntityToUserResponse(UsersEntity entity) {
    mapper.map(entity, userResponse);
    return userResponse;
  }

  // Generic convertion Page<Entity> to Page<Dto>
  private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
  }

}
