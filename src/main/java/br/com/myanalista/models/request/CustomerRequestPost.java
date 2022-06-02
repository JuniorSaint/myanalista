package br.com.myanalista.models.request;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.models.enums.CompanyTypeEnum;
import br.com.myanalista.models.enums.CustomerTypeEnum;
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
public class CustomerRequestPost {
  private String cnpjCpf;
  private CompanyTypeEnum companyType;  
  private String nickName;
  @NotEmpty(message = "Company name is mandatory field")
  private String companyName;
  private String fantasyName;
  private String address;
  private String number;
  private String complement;
  private String zipCode;
  private String district;
  private String city;
  // Start Financial
  private LocalDate contractDate;
  private CustomerTypeEnum customerType;
  private String typeOfContract;
  private Double contractValue;
  private String formOfPayment;
  private String cluster;
  // End Financial
  @Lob
  private String observation;
  private Set<Contacts> contacts;
  private Set<Teams> teams;
}
