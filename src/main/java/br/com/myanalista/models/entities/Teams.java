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
    private String memberCode;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private String memberLink;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customers customer;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToOne(mappedBy = "sellersOrder")
    private SellOut sellOutSellerOrder;

    @OneToOne(mappedBy = "sellerRegistration")
    private SellOut sellOutSellerRegistration;

    @OneToOne(mappedBy = "seller2")
    private SellOut sellOutSeller;

    @OneToOne(mappedBy = "sellerCode")
    private Lending lending;

    @OneToOne(mappedBy = "seller")
    private CustomerFromCustomer customerFromCustomer;
}
