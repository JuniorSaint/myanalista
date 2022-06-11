package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "clusterGec")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClusterGec implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String clusterGec;
  private String gecIne;

  @OneToOne(mappedBy = "cluster")
  private SellOut sellOut;

  @OneToOne(mappedBy = "gec")
  private Lending lending;

  @OneToOne(mappedBy = "clusterGec")
  private Customer customer;
}
