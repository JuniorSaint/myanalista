package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(length = 15)
  private String patrimony;
  private String code;
  private String description;
  private String power;
  private String manufacturingDate;
  private String serie;
  private String prop;
  private String situation;
  private Integer doors;
  private String observation;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name="distributor_id")
  private Distributor distributor;

  @OneToMany(mappedBy="equipmentNumber")
  private List<Lending> lendings;
}
