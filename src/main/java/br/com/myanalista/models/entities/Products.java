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
    private String code;
    @PrimaryKeyJoinColumn
    private CategoryProductList category;
    private String sku;
    private String productDescription;
    private boolean active;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    @OneToMany(mappedBy="product")
    private List<SellOut> sellOuts;
}
