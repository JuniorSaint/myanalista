package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Turnover;

@Repository
public interface TurnoverRepository extends JpaRepository<Turnover, Long>{
  Optional<Turnover> findById(Long id);
}
