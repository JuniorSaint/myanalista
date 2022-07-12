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
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/SUBCANAIS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        int index_1 = line.indexOf(";");
        int index_2 = line.indexOf(";", index_1 + 1);
        int index_3 = line.indexOf(";", index_2 + 1);
        int index_4 = line.indexOf(";", index_3 + 1);
        int index_5 = line.indexOf(";", index_4 + 1);
        int index_6 = line.indexOf(";", index_5 + 1);
        int index_7 = line.indexOf(";", index_6 + 1);




        SubChannel channelResp = SubChannel.builder()
            .subChannel(line.substring(0, index_1).trim())
            .code(line.substring(index_1 + 1, index_2).trim())
            .subChannelType(line.substring(index_2 + 1, index_3).trim())
            .focusRefPet(line.substring(index_3 + 1, index_4).trim())
            .focusDual(line.substring(index_4 + 1, index_5).trim())
            .subChannelIne(line.substring(index_5 + 1, index_6).trim())
            .channel(findChannel(line.substring(index_7 + 1).trim()))
            .build();

        repository.save(channelResp);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }

  private Channel findChannel(String code) {
      if(code.isEmpty()){
      return null;
    }
    Optional<Channel> response = repositoryChannel.findChannelByCode(code);
    if(!response.isPresent()){
      return null;
    }
    return response.get();
  }

}
