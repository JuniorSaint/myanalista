package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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

    @ManyToMany
    @JoinTable(name = "distributor_cluster", joinColumns = {@JoinColumn(name = "id_distributor")},
            inverseJoinColumns = {@JoinColumn(name = "id_cluster")})
    private List<ClusterGec> clusters;
    // End Financial
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnoreProperties(value = {"distributor"})
    private List<Lending> lendings;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnoreProperties(value = {"distributor"})
    private List<Equipment> equipment;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnoreProperties(value = {"distributor"})
    private List<SellOut> sellOuts;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnore
    private List<Teams> teams;

    @OneToMany(mappedBy = "distributor")
    @JsonIgnoreProperties(value = {"distributor"}) // Fix problem cyclic reference
    private List<Contacts> contacts;
}
