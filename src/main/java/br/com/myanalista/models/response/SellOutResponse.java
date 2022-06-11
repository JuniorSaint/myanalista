package br.com.myanalista.models.response;

import java.time.LocalDate;

import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SellOutResponse {
  private Long id;

  private String distributor;
  private LocalDate date;
  private Customer customer;
  private String route;
  private Teams sellersOrder;
  private String supervisorsOrder;
  private Teams sellerRegistration;
  private String supervisorRegistration;
  private String city;
  private String typeOperation;
  private String nfNumber;
  private Products product;
  private Double amount;
  private String liter;
  private String physicalBox;
  private String condition;
  private Double weight;
  private Double priceSell;
  private Double priceCost;
  private String tablePrice;
  private String groupR;  // product's group, don't put it;
  private String category; // categories don't put it;
  private String brand; // Brand don't need to put it;
  private Integer tableSell;
  private ClusterGec cluster;  // create register from sellout's table;
  private SubChannel channel;  // channel is consider subchannel to  myanlista then put subchannel on channel;
  private Integer averageTerm;
  private String cfop;
  private String fantasy;
  private String physicalJuridical;
  private String packages;
  private String ean;
  private String ncm;
  private String cia;
  private String unit;
  private String monthYear;
  private String comission;
  private String district;
  private Double sellPrice;
  private Double amountReturned;
  private Integer literReturned;
  private Integer physicalBoxReturned;
  private Double valueReturned;
  private Integer valueMeta;
  private Integer amountMeta;
  private Integer physicalBoxMeta;
  private String coverMeta;
  private String averageTermMeta;
  private Teams seller2;
  private String supervisor2;
  private String route2;
  private String quarter;
  private LocalDate orderDate;
  private Integer unitBox;
  private Integer unitBoxReturned;
  private Double unitBoxBox;
  private Integer unitBoxRmeta;
  private String register;
  private String area;
  private Integer discountCustomer;
  private String uf;
  private String toR;
  private String map;
  private String address;
  private String motiveReturn;
  private String productType;
  private Integer transaction; 
  private String consumptionType;
  private String year;
  private String active;
  private String purchase;
  private String equipment;
  private String gvSup;
  private Double adfFin;
  private Double cobBol;
  private String companyCategory;
  private String companySubCategory;
  private String companyReturnability;
  private String companyBrand;
  private String flavor;
  private String segment;
  private String consumptionOccasion;
  private LocalDate nfeDate;
  private String cutted;
  private String eliminated;
  private String replaced;
  private Double lastEntryCosts;
  private LocalDate  returnedDate;
  private String visitDay;
  private String salesPromoter;
  private String promoter2;
  private String commercialAction;
  private String polarizedStaggered;
}
