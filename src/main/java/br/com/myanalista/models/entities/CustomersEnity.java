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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomersEnity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cnpjCpf;
    private CompanyTypeEnum companyType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nickName_id", referencedColumnName = "id")
    private NickNamesEntity nickName;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formOfPayment_id", referencedColumnName = "id")
    private FormOfPayment formOfPayment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cluster_id", referencedColumnName = "id")
    private ClusterEntity cluster;
    // End Financial
    @Lob
    private String observation;
    @OneToMany(mappedBy="customer")
    private Set<ContactsEntity> contacts;
    @OneToMany(mappedBy="customer")
    private Set<TeamsEntity> teams;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
