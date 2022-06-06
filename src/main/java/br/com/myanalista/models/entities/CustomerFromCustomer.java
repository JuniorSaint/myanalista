package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "customerFromCustomer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFromCustomer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String code;
  private String route;
  private String cnpj;
  private String inscriçãoEstadual;
  private String companyName;
  private String fantasyName;
  private String address;
  private String nr;
  private String city;
  private String state;
  private String zipCode;
  private String district;
  private String phoneNumber;
  private String subChannel;
  private String week;
  private String sequence;
  private String email;
  private String Table;
  private String groupBusiness;
  private String seller;
  private String supervisor;
  private String area;
  private String originalPaymentMethod;
  private Integer maximunDays;
  private String turnover;
  private String regiterDay;
  private String inactivationDay;
  private String status;
  private String clusterGec;
  private String refPet;
  private String ls;
  private String rgb;
  private LocalDate lastPurchase;
  private Double creditLimit;
  private Double addition;
  private String seller2;
  private String week2;
  private String turnover2;
  private String distributor;
  private String latitude;
  private String longitude;
  private String DoesNotAllowCurrentRestChange;
  private String doNotChargeATicketBillingFee;
  private String cidCode;
  private String currentPaymentMethod;
  private String coverage;
  private String phoneNumber2;
  private String phoneNumber3;
  private String phoneNumber4;
  private String promoter;
  private String promoterEq2;
  private String channel;
  private String specie;

}
