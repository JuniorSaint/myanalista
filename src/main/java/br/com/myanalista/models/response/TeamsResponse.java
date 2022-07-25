package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Distributor;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsResponse extends RepresentationModel<TeamsResponse> {
  private Long id;
  private String memberCode;
  private String fullName;
  private String cpf;
  private String memberFunction;
  private String typeOfRegistrationMember;
  private Integer memberLink;
  private String sellerOrSupervisor;
  private Distributor distributor;
}
