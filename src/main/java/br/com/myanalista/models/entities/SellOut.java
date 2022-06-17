package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @ManyToOne
  @JoinColumn(name="customer_id")
  private Customer customer;
  private String route;
  @ManyToOne
  @JoinColumn(name="sellersOrder_id")
  private Teams sellersOrder;
  private String supervisorsOrder;
  @ManyToOne
  @JoinColumn(name = "sellerRegistration_id")
  private Teams sellerRegistration;
  private String supervisorRegistration;
  private String city;
  private String typeOperation;
  private String nfNumber;
  @ManyToOne
  @JoinColumn(name="product_id")
  private Products product;
  private Double amount;
  private String liter;
  private String physicalBox;
  private String condition;
  private Double weight;
  private Double priceSell;
  private String priceCost;
  private Double tablePrice;
  private String groupR; // product's group, don't put it;
  private String category; // categories don't put it;
  private String brand; // Brand don't need to put it;
  @ManyToOne
  @JoinColumn(name = "cluster_id")
  private ClusterGec cluster; // create register from sellout's table;
  @ManyToOne
  @JoinColumn(name = "channel_id")
  private SubChannel channel; // channel is consider subchannel to myanlista then put subchannel on channel;
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
  private String sellPrice;
  private Double amountReturned;
  private Double literReturned;
  private Integer physicalBoxReturned;
  private Double valueReturned;
  private Double valueMeta;
  private Integer amountMeta;
  private Integer physicalBoxMeta;
  private String coverMeta;
  private String averageTermMeta;
 @ManyToOne
  @JoinColumn(name = "seller2_id")
  private Teams seller2;
  private String supervisor2;
  private String route2;
  private String quarter;
  private LocalDate orderDate;
  private String unitBox;
  private String unitBoxReturned;
  private String unitBoxBox;
  private String unitBoxRmeta;
  private String register;
  private String area;
  private Double discountCustomer;
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
  private LocalDate returnedDate;
  private String visitDay;
  private String salesPromoter;
  private String promoter2;
  private String commercialAction;
  private String polarizedStaggered;
}
