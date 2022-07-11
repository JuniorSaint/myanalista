package br.com.myanalista.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DistributorRequestPost {

  private String cnpjCpf;
  private String companyType;
  private String nickName;
  private String companyName;
  private String fantasyName;
  private String address;
  private String number;
  private String complement;
  private String zipCode;
  private String district;
  private String city;
  private String state;
  private String observation;
  // Start Financial
  private LocalDate contractDate;
  private String customerType;
  private String typeOfContract;
  private Double ContractValue;
  private String formOfPayment;
  private String cluster;
  // End Financial
}
