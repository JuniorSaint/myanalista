//package br.com.myanalista.security.service;
//
//
//import br.com.myanalista.models.entities.Users;
//import br.com.myanalista.repositories.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	private  UserRepository repository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Users> user = repository.findByUserEmail(username);
//		if (user.isEmpty()) {
//			throw new UsernameNotFoundException("User [" + username + "] not found");
//		}
//		return new DetailDataUser(user) {
//		};
//	}
//
//}