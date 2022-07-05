package br.com.myanalista.repositories;

import java.util.Optional;

import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.response.EquipmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
  Optional<Equipment> findByCode(String code);

  Optional<Equipment> findByPatrimonyAndDistributor(String patrimony, Distributor distributor);

  @Query(value = "select e from Equipment e left join e.distributor where e.id = :id")
  Optional<Equipment> findByIpPerson(@Param(value = "id") Long id);
}
