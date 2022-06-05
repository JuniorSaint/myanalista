package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.repositories.SubChannelRepository;

@Service
public class SubChannelService {

  @Autowired
  private SubChannelRepository repository;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/SUBCANAIS.csv";


    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(",");

        SubChannel channel = SubChannel.builder()
            .subChannel(vector[0])
            .subChannelType(vector[1])
            .refPetFocus(vector[2])
            .dualFocus(vector[3])
            .subChannelIne(vector[4])
            .build();
     
        repository.save(channel);

        line = br.readLine();
      }   
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
