package br.com.myanalista.services;

import br.com.myanalista.models.entities.NationalHolidays;
import br.com.myanalista.repositories.NationalHolidayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NationalHolidayService {
  private NationalHolidayRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/HOLIDAYS.csv";

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

        NationalHolidays channel = NationalHolidays.builder()
            .date(LocalDate.parse(line.substring(0, index_1)))
            .dayOfTheWeek(line.substring(index_1 + 1, index_2))
            .month(line.substring(index_2 + 1, index_3))
            .description(line.substring(index_3 + 1, index_4))
            .city(line.substring(index_4 + 1, index_5))
            .territory(line.substring(index_5 + 1, index_6))
            .year(line.substring(index_6 + 1, index_7))
            .fixedDay(line.substring(index_7 + 1 ))       
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
