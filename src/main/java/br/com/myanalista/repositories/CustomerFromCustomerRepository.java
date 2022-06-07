package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.CustomerFromCustomer;

@Repository
public interface CustomerFromCustomerRepository extends JpaRepository<CustomerFromCustomer, Long>{
  Optional<CustomerFromCustomer> findByCode(String code);
  
}
