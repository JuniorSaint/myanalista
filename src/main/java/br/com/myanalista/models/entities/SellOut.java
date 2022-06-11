package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "sellOut")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellOut implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String distributor;
  private LocalDate date;
  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  private String route;
  @OneToOne
  @JoinColumn(name = "sellersOrder_id")
  private Teams sellersOrder;
  private String supervisorsOrder;
  @OneToOne
  @JoinColumn(name = "sellerRegistration_id")
  private Teams sellerRegistration;
  private String supervisorRegistration;
  private String city;
  private String typeOperation;
  private String nfNumber;
  @OneToOne
  @JoinColumn(name = "product_id")
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
  @OneToOne
  @JoinColumn(name = "cluster_id")
  private ClusterGec cluster;  // create register from sellout's table;
  @OneToOne
  @JoinColumn(name = "channel_id")
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
  @OneToOne
  @JoinColumn(name = "seller2_id")
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
