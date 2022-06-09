package br.com.myanalista.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String>{
 Optional<Channel> findChannelByCode(String code);
}
