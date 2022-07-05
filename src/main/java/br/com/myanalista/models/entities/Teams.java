package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teams implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String memberCode;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private Integer memberLink;
    private String sellerOrSupervisor;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "distributor_id", nullable = true)
    private Distributor distributor;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany(mappedBy="sellersOrder")
    @JsonIgnoreProperties(value = {"sellersOrder"})
    private List<SellOut> sellOutSellerOrders;

    @OneToMany(mappedBy="sellerRegistration")
    @JsonIgnoreProperties(value = {"sellerRegistration"})
    private List<SellOut> sellOutSellerRegistrations;

    @OneToMany(mappedBy = "seller2")
    @JsonIgnoreProperties(value = {"seller2"})
    private List<SellOut> sellOutSellers;

    @OneToMany(mappedBy = "sellerCode")
    @JsonIgnoreProperties(value = {"sellerCode"})
    private List<Lending> lendings;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties(value = {"seller"})
    private List<Customer> customers;

    @OneToMany(mappedBy = "sellerCustomer2")
    @JsonIgnoreProperties(value = {"sellerCustomer2"})
    private List<Customer> customerSeller2;
}
