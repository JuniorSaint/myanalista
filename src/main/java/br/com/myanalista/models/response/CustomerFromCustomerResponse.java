package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFromCustomerResponse {
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
  private SubChannel subChannel;
  private String week;
  private String sequence;
  private String email;
  private String tablePrice;
  private String groupBusiness;
  private Teams seller;
  private String supervisor;
  private String area;
  private String originalPaymentMethod;
  private String maximunDays;
  private String turnover;
  private String regiterDay;
  private String inactivationDay;
  private String status;
  private ClusterGec clusterGec;
  private String refPet;
  private String ls;
  private String rgb;
  private String lastPurchase;
  private String creditLimit;
  private String addition;
  private Teams seller2;
  private String week2;
  private String turnover2;
  private String distributor;
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
  private String channel;
  private String specie;
}
