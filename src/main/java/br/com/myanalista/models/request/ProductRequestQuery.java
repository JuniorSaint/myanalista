package br.com.myanalista.models.request;


import br.com.myanalista.models.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductRequestQuery {
    private Long id;
    private Integer sku;
    private String productDescription;
    private boolean active;
    private Categories categories;
}
