package br.com.myanalista.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

  @Singular
  @OneToMany(mappedBy="cluster")
  private List<SellOut> sellOuts;

  @OneToMany(mappedBy = "clusterGec")
  private List<Customer> customers;
}
