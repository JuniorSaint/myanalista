package br.com.myanalista.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.myanalista.models.entities.UsersEntity;
import br.com.myanalista.models.response.UserLoggedResponse;
import br.com.myanalista.models.response.UserResponse;


public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UserResponse> findByUserEmail(String userEmail);

    @Query("SELECT u FROM UserEntity u where u.userEmail = ?1")
    Optional<UserLoggedResponse> findByUserEmailProjection(String userEmail);

    Optional<UsersEntity> findById(Long id);

    Page<UsersEntity> findAll(Example example,  Pageable pageable);

    @Query("SELECT u FROM UserEntity  u ")
    List<UsersEntity> findSelectedList();  
}