package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Contacts;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Long>{
  
  Optional<Contacts> findById(Long id);
}
