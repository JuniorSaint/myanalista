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
@Table(name = "categoryGrand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGrand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_grand_id")
    private Categories categoryGrand;

    @ManyToOne
    @JoinColumn(name="category_father_id", nullable=false)
    private CategoryFather categoryFather;
}
