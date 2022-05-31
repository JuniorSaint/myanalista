package br.com.myanalista.models.request;

import javax.validation.constraints.NotEmpty;

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
  @NotEmpty(message = "Departament is mandatory field")
  private String contactDepartament;
  private String contactEmail;
  @NotEmpty(message = "Name is mandatory field")
  private String contactName;
  private String contactPhone;
  
}
