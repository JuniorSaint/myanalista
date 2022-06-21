package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.SubChannel;

@Repository
public interface SubChannelRepository extends JpaRepository<SubChannel, Long>{
  @Query(value = "select s from SubChannel s where s.subChannel = :subChannel")
Optional<SubChannel>  findSubChannelBysubChannel(@Param(value = "subChannel")String subChannel);

Optional<SubChannel> findSubChannelByCode(String code);
}
