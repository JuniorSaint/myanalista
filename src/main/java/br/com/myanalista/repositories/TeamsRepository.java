package br.com.myanalista.repositories;

import java.util.List;
import java.util.Optional;

import br.com.myanalista.models.response.TeamsSearchResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.entities.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {

    @Query(value = "select t from Teams t where t.fullName = :fullName")
    List<Teams> listToFull(@Param(value = "fullName") String fullName);

    Optional<Teams> findByMemberCode(String code);

    @Query(value = "select t from Teams t where t.memberCode = :code and t.distributor = :distributor")
    Optional<Teams> findMemberCodeAndDistributor(@Param(value = "code") String code, @Param(value = "distributor") Distributor distributor);

    long deleteByMemberCode(String code);

    @Query(value = "select c.id, c.fullName, c.cpf, c.memberFunction, c.typeOfRegistrationMember from Teams c ")
    Page<TeamsSearchResponse> findAllPageableAndSort(Pageable pageable, Example example);


}
