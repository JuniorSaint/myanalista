package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Distributor;
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
public class TeamsResponse {
  private String memberCode;
  private String fullName;
  private String cpf;
  private String memberFunction;
  private String typeOfRegistrationMember;
  private Integer memberLink;
  private String sellerOrSupervisor;
  private Distributor distributor;
}
