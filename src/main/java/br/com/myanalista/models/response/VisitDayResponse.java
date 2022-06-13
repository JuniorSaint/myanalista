package br.com.myanalista.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDayResponse {
  private Long id;
  private String daysOfWeek;
  private String firstDay;
  private String secondDay;
  private String thirdDay;
  private String fourthDay;
  private String fifthDay;
  private String sixDay;
}
