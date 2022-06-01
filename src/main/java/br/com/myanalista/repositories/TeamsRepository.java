package br.com.myanalista.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.TeamsEntity;

@Repository
public interface TeamsRepository extends JpaRepository<TeamsEntity, Long>{

  @Query(value = "select t from TeamsEntity t where t.fullName = :fullName")
  List<TeamsEntity> listToFull(@Param(value = "fullName") String fullName);

  Optional<TeamsEntity> findById(Long id);
}
