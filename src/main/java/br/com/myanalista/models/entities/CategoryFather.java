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
@Table(name = "categoryFather")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFather implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @ManyToOne
    @JoinColumn(name = "categoryFather_id")
    private Categories categoryFather;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategorySon categorySon;

    @OneToMany(mappedBy = "categoryFather")
    private List<CategoryFather> categoryFathers;

}
