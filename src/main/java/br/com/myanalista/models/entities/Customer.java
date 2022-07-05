package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "subChannel_id")
  private SubChannel subChannel;
  private String week;
  private String sequence;
  private String email;
  private String tablePrice;
  private String groupBusiness;
  @OneToOne
  @JoinColumn(name = "seller_id")
  private Teams seller;
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
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "clusterGec_id")
  private ClusterGec clusterGec;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "seller2_id")
  private Teams sellerCustomer2;
  private String week2;
  private String turnover2;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="distributor_id")
  private Distributor distributor;
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
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "channel_id")
  private Channel channel;
  private String specie;

  @Singular
  @OneToMany(mappedBy="customer")
  @JsonIgnoreProperties(value = {"customer"})
  private List<SellOut> sellOuts;

  @OneToMany(mappedBy = "customerRegistration")
  @JsonIgnoreProperties(value = {"customerRegistration"})
  private List<Lending> lending;
}
