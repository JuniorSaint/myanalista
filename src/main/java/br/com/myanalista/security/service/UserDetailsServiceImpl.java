package br.com.myanalista.security.service;

import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails authenticate(Users users) {
        UserDetails user = loadUserByUsername(users.getUserEmail());
        boolean matchesPassword = passwordEncoder.matches(users.getPassword(), user.getPassword());

        if (matchesPassword) {
            return user;
        }
        throw new BadRequestException("Password or email doesn't match");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = repository.findByUserEmail(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User " + username + "or password is not correct");
        }
        String[] roles = user.get().getAdmin() ? new String[]{"ADMINISTRATOR", "COLLABORATOR"} : new String[]{"COLLABORATOR"};
        user.get().setRoles(roles);
        return new DetailDataUser(user);
    }
}