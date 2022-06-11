package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

  @Query(value = "select c from Customer c where u.code = :code and u.distributor = :distributor ")
  Optional<Customer> findByCodeByDistributor(@Param("code") String code, @Param("distributor") String distributor);

  Optional<Customer> findByCode(String code);
  
}
