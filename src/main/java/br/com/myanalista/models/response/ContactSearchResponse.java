package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ContactSearchResponse extends RepresentationModel<ContactSearchResponse> {
  private Long id;
  private String contactDepartament;
  private String contactEmail;
  private String contactName;
  private String contactPhone;
}
