package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "Lending")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lending implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String territory;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "distributor_id")
  private Distributor distributor;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "customerRegistration_id")
  private Customer customerRegistration;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="cluster_id")
  private ClusterGec cluster;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="subChannel_id")
  private SubChannel subChannel;
  private String city;
  private Integer contract;
  private Integer amount;
  private LocalDateTime dateSend;
  private LocalDateTime dueDate;
  private Integer route;
  private Integer nfe;
  private String conservation;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "sellerCode_id")
  private Teams sellerCode;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="equipmentNumber_id")
  private Equipment equipmentNumber;
}
