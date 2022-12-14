package br.com.myanalista.models.request;


import java.util.List;

import br.com.myanalista.models.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductRequestPost {
    private Long id;
    @NotNull(message = "Code Sku is mandatory.")
    private Integer sku;
    @NotNull(message = "Description of the product is mandatory.")
    private String productDescription;
    private boolean active;
    private List<Categories> categories;
}


