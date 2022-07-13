package br.com.myanalista.services;

import br.com.myanalista.models.entities.Calendar;
import br.com.myanalista.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class CalendarService {
  @Autowired
  private CalendarRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/CALENDARIO.csv";

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
        int index_8 = line.indexOf(";", index_7 + 1);
        int index_9 = line.indexOf(";", index_8 + 1);
        int index_10 = line.indexOf(";", index_9 + 1);

        Calendar channel = Calendar.builder()
            .date(LocalDate.parse(line.substring(0, index_1)))
            .day(line.substring(index_1 + 1, index_2))
            .month(line.substring(index_2 + 1, index_3))
            .year(line.substring(index_3 + 1, index_4))
            .shortMonth(line.substring(index_4 + 1, index_5))
            .monthYear(line.substring(index_5 + 1, index_6))
            .dayOfTheWeek(line.substring(index_6 + 1, index_7))
            .dayOfTheWeekNumber(line.substring(index_7 + 1, index_8))
            .nationalHoliday(line.substring(index_8 + 1, index_9))
            .businessDayFiveDays(line.substring(index_9 + 1, index_10))
            .businessDaySixDays(line.substring(index_10 + 1))
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }
}
