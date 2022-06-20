package br.com.myanalista.security.service;

import java.util.Optional;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * @author raul@idip.com.br
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Users> responseUser = repository.findByUserEmail(email);
		if (responseUser.isEmpty()) {
			throw new BusinessException("It's not found user with email: " + email + responseUser.get());
		}

		return User.builder()
				.username(responseUser.get().getUserEmail())
				.password(responseUser.get().getPassword())
				.roles(String.valueOf(responseUser.get().getUserType()))
				.build();
	}

}