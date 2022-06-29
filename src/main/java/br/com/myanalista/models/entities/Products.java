package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private Integer sku;
    private String productDescription;
    private boolean active;
    @JsonIgnore
    @CreationTimestamp
    private LocalDate createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDate updatedAt;

    @Singular
    @OneToMany(mappedBy = "product")
    private List<SellOut> sellOuts;
    @JsonIgnoreProperties(value = {"products"})
    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories categories;
}
