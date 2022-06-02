package br.com.myanalista.models.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import br.com.myanalista.models.entities.Customers;
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
  @NotEmpty(message = "Fullname is mandatory field")
  private String fullName;
  private String memberCode;
  @CPF
  private String cpf;
  private String memberFunction;
  private String typeOfRegistrationMember;
  private String memberLink;
  private Customers customer;  
}
