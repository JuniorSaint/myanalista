package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email =:email")
    User findByEmailPerson(@Param("email") String email);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where lower(u.name) like lower(concat('%', :search, '%')) " +
            "or lower(u.email) like lower(concat('%', :search, '%'))")
    Page<User> findByNameOrEmail(@Param("search") String search, Pageable pageable);
    }

