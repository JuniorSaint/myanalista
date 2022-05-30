package br.com.myanalista.models.entities;

import br.com.myanalista.models.enums.CompanyTypeEnum;
import br.com.myanalista.models.enums.CustomerTypeEnum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEnity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cnpjCpj;
    private CompanyTypeEnum companyType;
    @JsonIgnoreProperties(value = {"customer"}) // Fix problem cyclic reference
    @OneToMany(mappedBy = "customer")
    private Set<NickNameEntity> nickNames;
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
    private Double ContractValue;
    private String formOfPayment;
    private String cluster;
    // End Financial
    @Lob
    private String observation;
    @OneToMany(mappedBy="customer")
    private Set<ContactEntity> contacts;
    @OneToMany(mappedBy="customer")
    private Set<TeamEntity> teams;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
