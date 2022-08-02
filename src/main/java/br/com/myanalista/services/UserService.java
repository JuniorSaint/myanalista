package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.controllers.UserController;
import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.exceptions.NotAuthorizateException;
import br.com.myanalista.models.entities.User;
import br.com.myanalista.models.request.ChangePasswordRequest;
import br.com.myanalista.models.request.LogInRequest;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.UserResponse;
import br.com.myanalista.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Utils utils;
    UserResponse userResponse = new UserResponse();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> users = repository.findByEmail(email);
        if (users.isEmpty()) {
            throw new EntityNotFoundException("Email " + email + "or password is not correct");
        }
        return users.get();
    }

    @Transactional
    public ResponseEntity<UserResponse> save(UserRequestPost userRequest) {
        Optional<User> searchForUser = repository.findByEmail(userRequest.getEmail());
        if (searchForUser.isPresent()) {
            throw new BadRequestException(
                    "Already exist user with this email: " + userRequest.getEmail() + ", try with another one");
        }
        userRequest.setPassword(encodePassword(userRequest.getPassword()));
        User userEntity = new User();
        mapper.map(userRequest, userEntity);
        User userCreated = repository.save(userEntity);
        UserResponse responeUser =  convertEntityToUserResponse(userCreated)
                .add(linkTo(methodOn(UserController.class).findAllUsers()).withRel("List of users"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(responeUser);
    }

    public ResponseEntity<Object> changePassword(ChangePasswordRequest request) {
        Optional<User> user = repository.findById(request.getId());
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not found with id: " + request.getId());
        }
        user.get().setPassword(encodePassword(request.getPassword()));
        User userUpdate = repository.save(user.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body("The password was changed with success of the user: " + userUpdate.getEmail());
    }

    @Transactional
    public ResponseEntity<UserResponse> update(UserRequestPut userRequestPut) {
        Optional<User> user = repository.findById(userRequestPut.getId());
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User not found with id: " + userRequestPut.getId());
        }
        userRequestPut.setPassword(encodePassword(userRequestPut.getPassword()));
        User userEntity = new User();
        mapper.map(userRequestPut, userEntity);
        User userUpdate = repository.save(userEntity);
        UserResponse responeUser =  convertEntityToUserResponse(userUpdate)
                .add(linkTo(methodOn(UserController.class).findAllUsers()).withRel("List of users"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(responeUser);
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("user not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted with success!");
    }

    public ResponseEntity<UserResponse> findById(Long id) {
        Optional<User> response = repository.findById(id);
        if (response.isEmpty()) {
            throw new EntityNotFoundException("There isn't user with this id: " + id);
        }
       UserResponse responeUser =  convertEntityToUserResponse(response.get())
               .add(linkTo(methodOn(UserController.class).findAllUsers()).withRel("List of users"));
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(responeUser);
    }

    public ResponseEntity<Page<UserResponse>> findAllWithPageSeek(String search, Pageable pageable) {
        Page<User> responses = repository.findByNameOrEmail(search, pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapEntityPageIntoDtoPage(responses, UserResponse.class));
    }

    public ResponseEntity<List<UserResponse>> findAllListed(){
        List<User> response = repository.findAll();
        List<UserResponse> resp = utils.mapListIntoDtoList(response, UserResponse.class);
        resp.stream().forEach(p -> p.add(linkTo(methodOn(UserController.class).findById(p.getId())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
    }

    public ResponseEntity<Boolean> validatePassword(LogInRequest logInRequest) {
        Optional<User> userResponse = repository.findByEmail(logInRequest.getEmail());
        if (userResponse.isEmpty()) {
            throw new NotAuthorizateException("there isn't authorization, password or email invalid.");
        }
        User user = userResponse.get();
        boolean valid = passwordEncoder.matches(logInRequest.getPassword(), user.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    // Convert entity to Dto
    private UserResponse convertEntityToUserResponse(User entity) {
        mapper.map(entity, userResponse);
        return userResponse;
    }

    public ResponseEntity<Page<UserResponse>> findAllWithPage(Pageable pageable) {
        Page<User> responses = repository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(utils.mapEntityPageIntoDtoPage(responses, UserResponse.class));
    }

    private String encodePassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder.encode(password);
    }
}
