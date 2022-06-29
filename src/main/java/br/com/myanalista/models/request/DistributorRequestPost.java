package br.com.myanalista.models.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import br.com.myanalista.models.entities.*;
import br.com.myanalista.models.enums.CompanyTypeEnum;
import br.com.myanalista.models.enums.CustomerTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DistributorRequestPost {

  private String cnpjCpf;
  private String companyType;
  private String nickName;
  @NotEmpty(message = "Company name is a mandatory field.")
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
  private CustomerTypeEnum customerType;
  private String typeOfContract;
  private Double ContractValue;
  private String formOfPayment;
  private String cluster;
  // End Financial
}
