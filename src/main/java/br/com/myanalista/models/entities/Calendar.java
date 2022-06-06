package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "Calendar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
