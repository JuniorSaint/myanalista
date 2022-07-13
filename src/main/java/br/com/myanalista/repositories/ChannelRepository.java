package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long>{
  @Query(value = "select c from Channel c where c.channel = :channel")
 Optional<Channel> findChannelBychannel(@Param(value = "channel")String channel);

    @Query(value = "select c from Channel c where c.code = :code")
    Optional<Channel> findChannelByCode(@Param(value = "code")String code);
}
