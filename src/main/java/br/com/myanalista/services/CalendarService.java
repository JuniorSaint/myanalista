package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Calendar;
import br.com.myanalista.repositories.CalendarRepository;

@Service
public class CalendarService {
  @Autowired
  private CalendarRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/CALENDARIO.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(",");

        Calendar channel = Calendar.builder()
            .date(LocalDate.parse(vector[0]))
            .day(vector[1])
            .month(vector[2])
            .year(vector[3])
            .shortMonth(vector[4])
            .monthYear(vector[5])
            .dayOfTheWeek(vector[6])
            .dayOfTheWeekNumber(vector[7])
            .nationalHoliday(vector[8])
            .businessDayFiveDays(vector[9])
            .businessDaySixDays(vector[10])
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
