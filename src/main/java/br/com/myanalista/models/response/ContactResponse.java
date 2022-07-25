package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Distributor;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ContactResponse extends RepresentationModel<ContactResponse> {
  private Long id;
  private String contactDepartament;
  private String contactEmail;
  private String contactName;
  private String contactPhone;
  private Distributor distributor;
}
