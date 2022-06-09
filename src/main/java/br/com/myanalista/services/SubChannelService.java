package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Channel;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.repositories.ChannelRepository;
import br.com.myanalista.repositories.SubChannelRepository;

@Service
public class SubChannelService {

  @Autowired
  private SubChannelRepository repository;

  @Autowired
  private ChannelRepository repositoryChannel;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/SUBCANAIS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");
        Optional<Channel> response = repositoryChannel.findChannelByCode(vector[7]);
        Channel channel = response.get();

        SubChannel channelResp = SubChannel.builder()
            .code(vector[1])
            .subChannel(vector[0])
            .subChannelType(vector[2])
            .focusRefPet(vector[3])
            .focusDual(vector[4])
            .subChannelIne(vector[5])
            .channel(channel)
            .build();            

        repository.save(channelResp);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }

}
