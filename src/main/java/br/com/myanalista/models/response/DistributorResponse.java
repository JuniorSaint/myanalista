package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Teams;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class DistributorResponse extends RepresentationModel<DistributorResponse> {
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
