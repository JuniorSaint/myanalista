package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class TurnoverResponse extends RepresentationModel<TurnoverResponse> {
  private Long id;
  private LocalDate calendarDate;
  private String turnoverByCalendar;  
}
