package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.response.ContactSearchResponse;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long>{
  
  Optional<Contacts> findById(Long id);

  @Query(value = "select c.id, c.contactEmail, c.contactName, c.contactPhone, c.contactDepartament from Contacts c ")
  Page<ContactSearchResponse> findAllPageableAndSort(Pageable pageable, Example example);

}
