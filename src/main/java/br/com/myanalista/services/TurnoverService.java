package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Turnover;
import br.com.myanalista.repositories.TurnoverRepository;

@Service
public class TurnoverService {

  @Autowired
  private TurnoverRepository repository;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/ROTATIVIDADE.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        Turnover channelResp = Turnover.builder()
            .calendarDate(convertDate(vector[0].trim()))
            .turnoverByCalendar(vector[1].trim())
            .build();

        repository.save(channelResp);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }

  private LocalDate convertDate(String date) {
    if (date.isEmpty()) {
      return null;
    }
    String[] splitString = date.split("/");
    if (splitString[0].isEmpty()) {
      return null;
    }
    String dateString = splitString[2] + "-" + splitString[1] + "-" + splitString[0];
    LocalDate dateConverted = LocalDate.parse(dateString);
    return dateConverted;
  }

}
