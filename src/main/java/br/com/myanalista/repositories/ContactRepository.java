package br.com.myanalista.repositories;

import java.util.Optional;

import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.response.ContactSearchResponse;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long> {

    Optional<Contacts> findById(Long id);

    Page<Contacts> findAll(Pageable pageable);

    @Query("select c from Contacts c where lower(c.contactName) like lower(concat('%', :search, '%')) " +
            "or lower(c.contactEmail) like lower(concat('%', :search, '%'))" +
            "or lower(c.contactDepartament) like lower(concat('%', :search, '%'))")
    Page<Contacts> findByContactNameOrContactEmailOrContactDepartament(@Param("search") String search, Pageable pageable);

}
