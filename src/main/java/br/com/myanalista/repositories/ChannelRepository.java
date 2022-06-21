package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>{
  @Query(value = "select c from Channel c where c.code = :code")
 Optional<Channel> findChannelByChannel(@Param(value = "code")String code);
}
