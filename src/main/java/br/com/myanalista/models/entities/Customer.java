package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String code;
  private String route;
  private String cnpj;
  private String inscrEstadual;
  private String companyName;
  private String fantasyName;
  private String address;
  private String number;
  private String city;
  private String state;
  private String zipCode;
  private String district;
  private String phoneNumber;
  private String week;
  private String sequence;
  private String email;
  private String tablePrice;
  private String groupBusiness;
  private String supervisor;
  private String area;
  private String originalPaymentMethod;
  private String maximunDays;
  private String turnover;
  private String regiterDay;
  private String inactivationDay;
  private String status;
  private String refPet;
  private String ls;
  private String rgb;
  private String lastPurchase;
  private String creditLimit;
  private String addition;
  private String week2;
  private String turnover2;
  private String latitude;
  private String longitude;
  private String notAllowCurrentRestChange;
  private String notChargeTicketBillingFee;
  private String cidCode;
  private String currentPaymentMethod;
  private String coverage;
  private String phoneNumber2;
  private String phoneNumber3;
  private String phoneNumber4;
  private String promoter;
  private String promoterEq2;
  private String specie;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "clusterGec_id")
  private ClusterGec clusterGec;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "seller2_id")
  private Teams sellerCustomer2;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="distributor_id")
  private Distributor distributor;

  @Singular
  @OneToMany(mappedBy="customer")
  @JsonIgnoreProperties(value = {"customer"})
  private List<SellOut> sellOuts;

  @OneToMany(mappedBy = "customerRegistration")
  @JsonIgnoreProperties(value = {"customerRegistration"})
  private List<Lending> lending;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "subChannel_id")
  private SubChannel subChannel;

  @OneToOne
  @JoinColumn(name = "seller_id")
  private Teams seller;
}
