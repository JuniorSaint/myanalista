package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.myanalista.models.entities.Channel;
import br.com.myanalista.repositories.ChannelRepository;

@Service
public class ChannelService {

  @Autowired
  private ChannelRepository repository;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/CANAIS.csv";


    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        Channel channel = Channel.builder()
            .code(vector[1])
            .channel(vector[0])
            .build();
     
        repository.save(channel);

        line = br.readLine();
      }   
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
