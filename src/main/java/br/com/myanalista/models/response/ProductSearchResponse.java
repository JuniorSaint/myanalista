package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductSearchResponse {
    private Long id;
    private String sku;
    private String productDescription;
    private boolean active;
    private List<CategoryMainResponse> categories;
}
