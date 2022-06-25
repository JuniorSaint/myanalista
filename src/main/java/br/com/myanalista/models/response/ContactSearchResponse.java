package br.com.myanalista.models.response;

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
public class ContactSearchResponse {
  private Long id;
  private String contactDepartament;
  private String contactEmail;
  private String contactName;
  private String contactPhone;
}