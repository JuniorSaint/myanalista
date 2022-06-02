package br.com.myanalista.models.response;


import java.util.Set;

import br.com.myanalista.models.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductResponse {
  private Long id;
  private Set<Categories> categories;
  private String sku;
  private String productDescription;
  private boolean isActive;
}
