package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.NationalHolidays;
import br.com.myanalista.repositories.NationalHolidayRepository;

@Service
public class NationalHolidayService {
  @Autowired
  private NationalHolidayRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/HOLIDAYS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(",");

        NationalHolidays channel = NationalHolidays.builder()
            .date(LocalDate.parse(vector[0]))
            .dayOfTheWeek(vector[1])
            .month(vector[2])
            .description(vector[3])
            .city(vector[4])
            .territory(vector[5])
            .year(vector[6])
            .fixedDay(vector[7])       
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
