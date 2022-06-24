package br.com.myanalista.models.response;

import java.util.List;

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
  private String sku;
  private String productDescription;
  private boolean active;
  private List<Categories> categories;
}
