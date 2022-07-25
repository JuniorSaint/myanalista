package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDayResponse extends RepresentationModel<VisitDayResponse> {
  private Long id;
  private String daysOfWeek;
  private String firstDay;
  private String secondDay;
  private String thirdDay;
  private String fourthDay;
  private String fifthDay;
  private String sixDay;
}
