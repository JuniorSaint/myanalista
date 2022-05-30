package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "clusters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClusterEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String cluster;
  @OneToOne(mappedBy = "cluster")
  private CustomersEnity customer;
  private CategoriesEntity category;
  @CreationTimestamp
  private LocalDate createdAt;
  @UpdateTimestamp
  private LocalDate updatedAt;
}
