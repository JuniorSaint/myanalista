package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
  Optional<Customers> findById(Long id);
}
