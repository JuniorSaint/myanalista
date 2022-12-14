package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "sellOut")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class SellOut implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
    private LocalDate date;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String route;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sellersOrder_id")
    private Teams sellersOrder;
    private String supervisorsOrder;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sellerRegistration_id")
    private Teams sellerRegistration;
    private String supervisorRegistration;
//    private String city;
    private String typeOperation;
    private String nfNumber;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
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
//    private String category;
    private String brand; // Brand don't need to put it;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cluster_id")
    private ClusterGec cluster; // create register from sellout's table;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "channel_id")
    private SubChannel channel; // channel is consider subchannel to myanlista then put subchannel on channel;
    private Integer averageTerm;
    private String cfop;
//    private String fantasy;
    private String physicalJuridical;
    private String packages;
    private String ean;
    private String ncm;
    private String cia;
    private String unit;
    private String monthYear;
    private String comission;
//    private String district;
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
    @JsonIgnore
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
//        @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer register;
    private String area;
    private Double discountCustomer;
//    private String uf;
    private String toR;
    private String map;
//    private String address;
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
