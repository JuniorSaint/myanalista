package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Route;
import br.com.myanalista.repositories.RouteRepository;

@Service
public class RouteService {
  @Autowired
  private RouteRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/ROTAS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        Route channel = Route.builder()
            .turnover(vector[0])
            .route(vector[1])
            .firstDay(vector[2])
            .secondDay(vector[3])
            .thirdDay(vector[4])
            .fourthDay(vector[5])
            .fifthDay(vector[6])
            .sixDay(vector[7])
            .amount(vector[8])
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
