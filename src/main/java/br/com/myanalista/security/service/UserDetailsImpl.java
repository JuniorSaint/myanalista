package br.com.myanalista.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author raul@idip.com.br
 */
@Getter
@Setter
@Builder
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    Long guid;
    String name;
    String username;
    String presentation;
    String email;
    Collection<? extends GrantedAuthority> authorities;
    @JsonIgnore
    private String password;

    public static UserDetailsImpl build(User user) {

        return UserDetailsImpl.builder().email(user.getUsername())
                .password(user.getPassword()).build();
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