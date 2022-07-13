package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.entities.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {

    @Query(value = "select t from Teams t where t.fullName = :fullName")
    List<Teams> listToFull(@Param(value = "fullName") String fullName);

    Optional<Teams> findByMemberCode(String code);

    @Query(value = "select t from Teams t where t.memberCode = :code and t.distributor = :distributor")
    Optional<Teams> findMemberCodeAndDistributor(@Param(value = "code") String code, @Param(value = "distributor") Distributor distributor);

    Optional<Teams> findByMemberCodeAndDistributor(String code, Distributor distributor);

    long deleteByMemberCode(String code);

    Page<Teams> findAll(Pageable pageable);

    @Query("select t from Teams t where lower(t.fullName) like lower(concat('%', :search, '%')) " +
            "or lower(t.memberCode) like lower(concat('%', :search, '%'))" +
            "or lower(t.memberFunction) like lower(concat('%', :search, '%'))" +
            "or lower(t.typeOfRegistrationMember) like lower(concat('%', :search, '%'))" +
            "or lower(t.cpf) like lower(concat('%', :search, '%'))")
    Page<Teams> findByFullNameOrmemberCode(@Param("search") String search, Pageable pageable);


}
