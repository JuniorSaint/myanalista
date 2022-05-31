package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.CustomersEnity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomersEnity, Long> {
  Optional<CustomersEnity> findById(Long id);
}
