package br.com.myanalista.models.response;

import java.time.LocalDate;
import java.util.Set;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Teams;
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
public class DistributorResponse {

    private Long id;
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
    // Start Financial
    private LocalDate contractDate;
    private String customerType;
    private String typeOfContract;
    private Double ContractValue;
    private String formOfPayment;
    private String cluster;
    // End Financial
    private String observation;
    private Set<Contacts> contacts;
    private Set<Teams> teams;
  
}
