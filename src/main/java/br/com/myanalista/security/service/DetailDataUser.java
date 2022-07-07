package br.com.myanalista.security.service;


import br.com.myanalista.models.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DetailDataUser implements UserDetails {

    private final Optional<Users> users;

    public DetailDataUser(Optional<Users> users) {
        this.users = users;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = Arrays.stream(users.get().getRoles())
//                .map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
//        return authorities;
        return null;
    }

    @Override
    public String getPassword() {
        return users.orElse(new Users()).getPassword();
    }

    @Override
    public String getUsername() {
        return users.orElse(new Users()).getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}