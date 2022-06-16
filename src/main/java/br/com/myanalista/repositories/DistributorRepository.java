package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
  @Query(value = "select d from Distributor d where d.cnpjCpf = :cnpjCpf")
  Optional<Distributor> findDistributorByCnpj(@Param("cnpjCpf") String cnpjCpf);
}
