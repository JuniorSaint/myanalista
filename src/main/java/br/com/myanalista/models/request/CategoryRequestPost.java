package br.com.myanalista.models.request;


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
public class CategoryRequestPost {
  private String categoryName;
  private Products product;
  private Categories category;
}
