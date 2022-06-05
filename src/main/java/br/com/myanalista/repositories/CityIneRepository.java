package br.com.myanalista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.CityIne;

@Repository
public interface CityIneRepository extends JpaRepository<CityIne, Long> {
  
}
