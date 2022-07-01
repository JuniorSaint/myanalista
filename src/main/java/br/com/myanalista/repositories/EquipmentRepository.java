package br.com.myanalista.repositories;

import java.util.Optional;

import br.com.myanalista.models.entities.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
  Optional<Equipment> findByCode(String code);

  Optional<Equipment> findByPatrimonyAndDistributor(String patrimony, Distributor distributor);
}
