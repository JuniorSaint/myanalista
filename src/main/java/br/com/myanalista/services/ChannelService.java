package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Users;
import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.models.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.myanalista.models.entities.Channel;
import br.com.myanalista.repositories.ChannelRepository;

@Service
public class ChannelService {

  @Autowired
  private ChannelRepository repository;
  @Autowired
  private ModelMapper mapper;
  @Autowired
  private Utils utils;

  public ChannelResponse findById(Long id){
    Optional<Channel> channel = repository.findById(id);
    if(channel.isEmpty()){
      throw new BusinessException("There isn't channel with id: " + id);
    }
    ChannelResponse channelResponse = new ChannelResponse();
    mapper.map(channel.get(), channelResponse);
    return channelResponse;
  }

  public Page<ChannelResponse> findAllWithPage(Pageable pageable) {
    Page<Channel> responses = repository.findAll(pageable);
    return utils.mapEntityPageIntoDtoPage(responses, ChannelResponse.class);
  }

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/CANAIS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        int index_1 = line.indexOf(";");

        Channel channel = Channel.builder()
            .channel(line.substring(0, index_1))
            .code(line.substring(index_1 + 1))
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
