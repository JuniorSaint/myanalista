package br.com.myanalista.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long>{

  @Query(value = "select t from Teams t where t.fullName = :fullName")
  List<Teams> listToFull(@Param(value = "fullName") String fullName);

  Optional<Teams> findByMemberCode(String code);

  long deleteByMemberCode(String code);
}
