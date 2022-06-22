package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Entity
@Table(name = "categorySon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "categorySon_id")
    private Categories categorySon;

    @OneToMany(mappedBy = "categoryFather")
    private List<CategoryFather> categoryFather;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products products;
}
