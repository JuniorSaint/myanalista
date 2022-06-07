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
  private String customer;
  private String route;
  private String sellersOrder;
  private String supervisorsOrder;
  private String sellerRegistration;
  private String supervisorRegistration;
  private String city;
  private String typeOperation;
  private String nfNumber;
  private String product;
  private Integer amount;
  private String liter;
  private String physicalBox;
  private String condition;
  private Double weight;
  private Double priceSell;
  private Double priceCost;
  private String tablePrice;
  private String group;
  private String category;
  private String brand;
  private String tableSell;
  private String cluster;
  private String channel;
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
  private Integer amountReturned;
  private Integer literReturned;
  private Integer physicalBoxReturned;
  private Double valueReturned;
  private Double valueMeta;
  private Integer amountMeta;
  private Integer physicalBoxMeta;
  private Integer coverMeta;
  private Double averageTermMeta;
  private String seller2;
  private String supervisor2;
  private String route2;
  private String quarter;
  private LocalDate orderDate;
  private Integer unitBox;
  private Integer unitBoxReturned;
  private Integer unitBoxBox;
  private Integer unitBoxRmeta;
  private String register;
  private String area;
  private Double discountCustomer;
  private String uf;
  private String to;
  private String map;
  private String address;
  private String motiveReturn;
  private String productType;
  private String transaction; 
  private String consumptionType;
  private String year;
  private Boolean active;
  private String purchase;
  private String equipment;
  private String gvSup;
  private String adfFin;
  private String cobBol;
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
