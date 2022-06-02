package br.com.myanalista.models.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

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
  @NotEmpty(message = "Category name is mandatory field")
  private String categoryName;
  private Products product;
  private Set<Categories> categories;
  private Categories category;

}
