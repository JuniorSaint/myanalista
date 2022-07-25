package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Builder
@Entity
@Table(name = "Calendar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Calendar implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private LocalDate date;
  private String day;
  private String month;
  private String year;
  private String shortMonth;
  private String monthYear;
  private String dayOfTheWeek;
  private String dayOfTheWeekNumber;
  private String nationalHoliday;
  private String businessDayFiveDays;
  private String businessDaySixDays;

}
