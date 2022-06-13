package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.myanalista.models.enums.TeamTypeEnum;

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
    private String memberCode;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private String memberLink;
    private TeamTypeEnum sellerOrSupervisor;
    // @OneToMany(mappedBy="cart")
    // private List<Teams> seller;
    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = true)
    private Distributor distributor;
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
    private Customer customer;
}
