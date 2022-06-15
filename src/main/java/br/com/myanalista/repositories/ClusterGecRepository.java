package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.ClusterGec;

@Repository
public interface ClusterGecRepository  extends JpaRepository<ClusterGec, Long>{
  @Query(value = "select c from ClusterGec c where c.clusterGec = :cluster")
  Optional<ClusterGec> findByClusterGec(@Param(value = "cluster") String cluster);  
}
