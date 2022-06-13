package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.VisitDay;
import br.com.myanalista.repositories.VisitDayRepository;

@Service
public class VisitDayService {
  @Autowired
  private VisitDayRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/DIA_VISITA.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        VisitDay channel = VisitDay.builder()
            .daysOfWeek(vector[0])
            .firstDay(vector[1])
            .secondDay(vector[2])
            .thirdDay(vector[3])
            .fourthDay(vector[4])
            .fifthDay(vector[5])
            .sixDay(vector[6])
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
