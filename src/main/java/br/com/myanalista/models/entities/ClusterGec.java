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
  private String code;
  private String clusterGec;
  private String gecIne;

  @OneToOne(mappedBy = "cluster")
  private SellOut sellOut;
}
