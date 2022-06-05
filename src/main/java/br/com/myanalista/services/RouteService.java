package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.myanalista.models.entities.Route;
import br.com.myanalista.repositories.RouteRepository;

public class RouteService {
  @Autowired
  private RouteRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/ROTAS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(",");

        Route channel = Route.builder()
            .turnover(vector[0])
            .route(vector[1])
            .firstDay(Integer.parseInt(vector[2]))
            .secondDay(Integer.parseInt(vector[3]))
            .thirdDay(Integer.parseInt(vector[4]))
            .fourthDay(Integer.parseInt(vector[5]))
            .fifthDay(Integer.parseInt(vector[6]))
            .sixDay(Integer.parseInt(vector[7]))
            .amount(Integer.parseInt(vector[8]))
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
