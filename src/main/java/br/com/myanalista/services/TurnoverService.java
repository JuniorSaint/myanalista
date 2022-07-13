package br.com.myanalista.services;

import br.com.myanalista.models.entities.Turnover;
import br.com.myanalista.repositories.TurnoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class TurnoverService {

  @Autowired
  private TurnoverRepository repository;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/ROTATIVIDADE.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        int index_1 = line.indexOf(";");

        Turnover channelResp = Turnover.builder()
            .calendarDate(convertDate(line.substring(0, index_1).trim()))
            .turnoverByCalendar(line.substring(index_1 + 1).trim())
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
