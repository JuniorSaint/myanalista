package br.com.myanalista.services;

import br.com.myanalista.configs.GeralConfig;
import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.exceptions.NotFoundById;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.models.request.ChangePasswordRequest;
import br.com.myanalista.models.request.UserRequestPost;
import br.com.myanalista.models.request.UserRequestPut;
import br.com.myanalista.models.response.ProductResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Utils utils;
    UserResponse userResponse = new UserResponse();

    @Transactional
    public UserResponse save(UserRequestPost userRequest) {
        Optional<Users> searchForUser = repository.findByUserEmail(userRequest.getUserEmail());
        if (searchForUser.isPresent()) {
            throw new NotFoundById(
                    "Already exist user with this email: " + userRequest.getUserEmail() + ", try with another one");
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Users userEntity = new Users();
        mapper.map(userRequest, userEntity);
        Users userCreated = repository.save(userEntity);
        return convertEntityToUserResponse(userCreated);
    }

    public String changePassword(ChangePasswordRequest request) {
        Optional<Users> user = repository.findById(request.getId());
        if (!user.isPresent()) {
            throw new NotFoundById("User not found with id: " + request.getId());
        }
        user.get().setPassword(passwordEncoder.encode(request.getPassword()));
        Users userUpdate = repository.save(user.get());
        return "The password was changed with success of the user: " + userUpdate.getUserName();
    }

    @Transactional
    public UserResponse update(UserRequestPut userRequestPut) {
        Optional<Users> user = repository.findById(userRequestPut.getId());
        if (!user.isPresent()) {
            throw new BusinessException("User not found with id: " + userRequestPut.getId());
        }
        userRequestPut.setPassword(passwordEncoder.encode(userRequestPut.getPassword()));
        Users userEntity = new Users();
        mapper.map(userRequestPut, userEntity);
        Users userUpdate = repository.save(userEntity);
        return convertEntityToUserResponse(userUpdate);
    }

    @Transactional
    public String delete(Long id) {
        Optional<Users> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundById("user not found with id: " + id);
        }
        repository.deleteById(id);
        return "User deleted with success";
    }

    public UserResponse findById(Long id) {
        Optional<Users> response = repository.findById(id);
        if (response.isEmpty()) {
            throw new NotFoundById("There isn't user with this id: " + id);
        }
        return convertEntityToUserResponse(response.get());
    }

    public Page<UserResponse> getUserByTerm(Users users, Integer page) {
        Sort sort = Sort.by("userName").descending();
        Pageable pageable = PageRequest.of(page, 20, sort);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(StringMatcher.STARTING)
                .withIgnoreCase();
        Example<Users> example = Example.of(users, matcher);
        Page<Users> respFromRepository = repository.findAll(example, pageable);
        Page<UserResponse> response = mapEntityPageIntoDtoPage(respFromRepository, UserResponse.class);
        return response;
    }

    // Convert entity to Dto
    private UserResponse convertEntityToUserResponse(Users entity) {
        mapper.map(entity, userResponse);
        return userResponse;
    }

    // Generic convertion Page<Entity> to Page<Dto>
    private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
    }
    public Page<UserResponse> findAllWithPage(Pageable pageable) {
        Page<Users> responses = repository.findAll(pageable);
        return utils.mapEntityPageIntoDtoPage(responses, UserResponse.class);
    }
}
