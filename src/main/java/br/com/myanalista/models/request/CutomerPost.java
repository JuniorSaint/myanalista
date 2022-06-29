package br.com.myanalista.models.request;

import java.time.LocalDate;

import br.com.myanalista.models.entities.Teams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class CutomerPost {
  @NotEmpty(message = "Code is a mandatory field.")
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
  private String subChannel;
  private String week;
  private String sequence;
  private String email;
  private String tablePrice;
  private String groupBusiness;
  private Teams seller;
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
  private Teams seller2;
  private String week2;
  private String turnover2;
  @NotEmpty(message = "Distributor is a mandatory field.")
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
