package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String>{
  Optional<Equipment> findByCode(String code);
}
