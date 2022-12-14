package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Channel;
import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.repositories.ChannelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChannelService {

  private ChannelRepository repository;
  private ModelMapper mapper;
  private Utils utils;

  public ChannelResponse findById(Long id){
    Optional<Channel> channel = repository.findById(id);
    if(channel.isEmpty()){
      throw new EntityNotFoundException("There isn't channel with id: " + id);
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
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/CANAIS.csv";

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
