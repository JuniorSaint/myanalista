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
  private Long id;
  private String fullName;
  private String memberCode;
  private String cpf;
  private String memberFunction;
  private String typeOfRegistrationMember;
  private String memberLink;
  private Distributor customer;
}
