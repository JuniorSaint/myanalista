package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.ContactsEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactsEntity, Long>{
  
  Optional<ContactsEntity> findById(Long id);
}
