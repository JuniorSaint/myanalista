package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String patrimony;
  private String code;
  private String description;
  private String power;
  private LocalDate manufacturingDate;
  private String serie;
  private String prop;
  private String situation;
  private Integer doors;
  private String observation;

  @OneToOne(mappedBy = "equipmentNumber")
  private Lending lending;
}
