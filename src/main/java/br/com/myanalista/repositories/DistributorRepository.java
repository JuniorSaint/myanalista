package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
  Optional<Distributor> findById(Long id);
}
