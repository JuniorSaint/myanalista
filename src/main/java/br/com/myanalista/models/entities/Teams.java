package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long id;
    private String memberCode;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private Integer memberLink;
    private String sellerOrSupervisor;
    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = true)
    private Distributor distributor;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany(mappedBy="sellersOrder")
    private List<SellOut> sellOutSellerOrders;

    @OneToMany(mappedBy="sellerRegistration")
    private List<SellOut> sellOutSellerRegistrations;

    @OneToMany(mappedBy = "seller2")
    private List<SellOut> sellOutSellers;

    @OneToMany(mappedBy = "sellerCode")
    private List<Lending> lendings;

    @OneToMany(mappedBy = "seller")
    private List<Customer> customers;

    @OneToMany(mappedBy = "sellerCustomer2")
    private List<Customer> customerSeller2;
}
