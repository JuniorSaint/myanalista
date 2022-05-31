package br.com.myanalista.models.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import br.com.myanalista.models.entities.CategoriesEntity;
import br.com.myanalista.models.entities.ProductsEntity;
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
  private ProductsEntity product;
  private Set<CategoriesEntity> categories;
  private CategoriesEntity category;

}
