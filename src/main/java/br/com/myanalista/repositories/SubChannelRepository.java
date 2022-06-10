package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.SubChannel;

@Repository
public interface SubChannelRepository extends JpaRepository<SubChannel, String>{
Optional<SubChannel>  findSubChannelBysubChannel(String subChannel);

Optional<SubChannel> findSubChannelByCode(String code);
}
