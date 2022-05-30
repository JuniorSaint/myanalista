package br.com.myanalista.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.myanalista.exceptions.ErrorToConnect;
import br.com.myanalista.models.entities.UsersEntity;
import br.com.myanalista.models.request.UserRequestPost;
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
      if(!searchForUser.isEmpty()){
        throw new ErrorToConnect("Already exist user with this email: "+ userRequest.getUserEmail() + ", try with another one");
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

  public Optional<UserResponse> getUserByEmail(String email){
    Optional<UserResponse> resp =  repository.findByUserEmail(email); 
    if(resp.isEmpty()){
      throw new ErrorToConnect("There isn't user with this email: " + email );
    }
    return resp;
  }

  public List<UserResponse> getUserByTerm(String term){
    return null;
  }

}
