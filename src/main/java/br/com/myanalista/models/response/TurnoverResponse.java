package br.com.myanalista.models.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TurnoverResponse {

  private Long id;
  private LocalDate calendarDate;
  private String turnoverByCalendar;  
}
