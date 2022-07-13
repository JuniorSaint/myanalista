package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.CityIne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityIneRepository extends JpaRepository<CityIne, Long> {
  Optional<CityIne>  findById(Long id);
}
