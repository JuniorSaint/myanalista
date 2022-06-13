package br.com.myanalista.models.entities;

import br.com.myanalista.models.enums.CompanyTypeEnum;
import br.com.myanalista.models.enums.CustomerTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@Table(name = "distributor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distributor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cnpjCpf;
    private CompanyTypeEnum companyType;
    private String nickName;
    private String companyName;
    private String fantasyName;
    private String address;
    private String number;
    private String complement;
    private String zipCode;
    private String district;
    private String city;
    @Lob
    private String observation;
    // Start Financial
    private LocalDate contractDate;
    private CustomerTypeEnum customerType;
    private String typeOfContract;
    private Double ContractValue;
    private String formOfPayment;
    private String cluster;
    // End Financial
    @JsonIgnoreProperties(value = {"distributor"}) // Fix problem cyclic reference
    @OneToMany(mappedBy="distributor")
    private List<Contacts> contacts;
    @JsonIgnoreProperties(value = {"distributor"}) // Fix problem cyclic reference
    @OneToMany(mappedBy="distributor")
    private List<Teams> teams;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}