package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ClusterGec implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clusterGec;
    private String gecIne;

    @Singular
    @OneToMany(mappedBy = "cluster")
    private List<SellOut> sellOuts;

    @OneToMany(mappedBy = "clusterGec")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "cluster")
    @JsonIgnoreProperties(value = {"cluster"})
    private List<Lending> lendings;
}
