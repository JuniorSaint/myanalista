package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.Turnover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Long>{
  Optional<Turnover> findById(Long id);
}
