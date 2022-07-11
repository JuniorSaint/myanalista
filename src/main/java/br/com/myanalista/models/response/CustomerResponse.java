package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
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
  private ClusterGec clusterGec;
  private Teams sellerCustomer2;
  private Channel channel;
  private Distributor distributor;
  private SubChannel subChannel;
  private Teams seller;
  private List<SellOut> sellOuts;
  private List<Lending> lending;
}
