package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
//import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Map;
import java.util.Optional;

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
        ;
        return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToUserResponse(userCreated));
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
        return ResponseEntity.status(HttpStatus.OK).body(convertEntityToUserResponse(userUpdate));
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
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(convertEntityToUserResponse(response.get()));
    }

    public ResponseEntity<Page<UserResponse>> getUserByTerm(User users, Integer page) {
        Sort sort = Sort.by("userName").descending();
        Pageable pageable = PageRequest.of(page, 20, sort);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(StringMatcher.STARTING)
                .withIgnoreCase();
        Example<User> example = Example.of(users, matcher);
        Page<User> respFromRepository = repository.findAll(example, pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(mapEntityPageIntoDtoPage(respFromRepository, UserResponse.class));
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

    // Generic convertion Page<Entity> to Page<Dto>
    private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
    }

    public ResponseEntity<Page<UserResponse>> findAllWithPage(Pageable pageable) {
        Page<User> responses = repository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(utils.mapEntityPageIntoDtoPage(responses, UserResponse.class));
    }

    private String encodePassword(String password){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder.encode(password);
    }}
