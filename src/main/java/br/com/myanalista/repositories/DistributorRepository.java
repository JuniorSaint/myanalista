package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.Distributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    @Query(value = "select d from Distributor d where d.cnpjCpf = :cnpjCpf")
    Optional<Distributor> findDistributorByCnpj(@Param("cnpjCpf") String cnpjCpf);
    @Query(value = "select c from Distributor c left join c.teams where c.id = :id")
    Distributor findDistribuitorById(@Param(value = "id") Long id);
    Page<Distributor> findAll(Pageable pageable);
    @Query(value = "select c from Distributor c where c.id = :id")
    List<Distributor> findAllById(@Param(value = "id") Long id);
    @Query("select d from Distributor d where lower(d.nickName) like lower(concat('%', :search, '%')) " +
            "or lower(d.companyName) like lower(concat('%', :search, '%'))" +
            "or lower(d.companyType) like lower(concat('%', :search, '%'))" +
            "or lower(d.cnpjCpf) like lower(concat('%', :search, '%'))" )
    Page<Distributor> findByNickNameOrCompanyNameOrCnpjCpf(@Param("search") String search, Pageable pageable);

}
