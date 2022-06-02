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
import br.com.myanalista.models.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select u from Users u where u.userName = :userName")
    List<Users> findAll(@Param(value = "userName") String userName);

    Optional<Users> findByUserEmail(String userEmail);

    Optional<Users> findById(Long id);

    Page<Users> findAll(Example example, Pageable pageable);
}