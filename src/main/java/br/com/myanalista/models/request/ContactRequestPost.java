package br.com.myanalista.models.request;

import javax.validation.constraints.NotEmpty;

import br.com.myanalista.models.entities.CustomersEnity;
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

public class ContactRequestPost {

  private String contactDepartament;
  private String contactEmail;
  private String contactName;
  private String contactPhone;  
  private CustomersEnity customer;
}
