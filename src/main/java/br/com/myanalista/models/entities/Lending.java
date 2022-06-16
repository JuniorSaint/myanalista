package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
  @OneToOne
  @JoinColumn(name = "distributor_id")
  private Distributor distributor;
  @ManyToOne
  @JoinColumn(name = "customerRegistration_id")
  private Customer customerRegistration;
  private String gec;
  @ManyToOne
  @JoinColumn(name="subChannel_id")
  private SubChannel subChannel;
  private String city;
  @OneToOne
  @JoinColumn(name = "equipmentNumber_id")
  private Equipment equipmentNumber;
  private String contract;
  private Integer amount;
  private LocalDate dateSend;
  private LocalDate dueDate;
  @ManyToOne
  @JoinColumn(name = "sellerCode_id")
  private Teams sellerCode;
  private String route;
  private String nfe;
  private String conservation;
}
