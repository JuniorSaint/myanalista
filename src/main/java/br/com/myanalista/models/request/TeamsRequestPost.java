package br.com.myanalista.models.request;

import br.com.myanalista.models.entities.Distributor;
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
public class TeamsRequestPost {

  private String fullName;
  private String memberCode;
  private String cpf;
  private String memberFunction;
  private String typeOfRegistrationMember;
  private String memberLink;
  private Distributor distributor;  
}
