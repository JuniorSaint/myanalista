package br.com.myanalista.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    @Query(value = "select u from UsersEntity u where u.userName = :userName")
    List<UsersEntity> findAll(@Param(value = "userName") String userName);

    Optional<UsersEntity> findByUserEmail(String userEmail);

    Optional<UsersEntity> findById(Long id);

    Page<UsersEntity> findAll(Example example, Pageable pageable);
}