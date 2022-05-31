package br.com.myanalista.models.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import br.com.myanalista.models.entities.CategoriesEntity;
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
public class ProductRequestPost {
  private Set<CategoriesEntity> categories;
  @NotEmpty(message = "Sku is mandatory field")
  private String sku;
  @NotEmpty(message = "Description of product is mandatory field")
  private String productDescription;
  @NotEmpty(message = "Product status is mandatory field")
  private boolean isActive;
}
