package br.com.myanalista.models.response;

import java.util.Set;

import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.entities.Products;
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
public class CategoryResponse {
  private Long id;
  private String categoryName;
  private Products product;
  private Set<Categories> categories;
  private Categories category;
}
