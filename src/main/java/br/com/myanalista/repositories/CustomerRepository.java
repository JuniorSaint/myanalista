package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.Distributor;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

  @Query(value = "select c from Customer c where c.cnpj = :cnpj and c.distributor = :distributor ")
  Optional<Customer> findByCodeByDistributor(@Param("cnpj") String cnpj, @Param("distributor") Distributor distributor);

  Optional<Customer> findByCode(String code);
  @Query(value = "select c from Customer c where c.cnpj = :cnpj")
  Optional<Customer> findCustomerByCnpj(@Param(value = "cnpj") String cnpj);
}
