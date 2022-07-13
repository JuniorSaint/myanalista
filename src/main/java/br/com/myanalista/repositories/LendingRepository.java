package br.com.myanalista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.Lending;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Long>{
  
  
}
