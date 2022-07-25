package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Categories;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProductResponse extends RepresentationModel<ProductResponse> {
  private Long id;
  private String sku;
  private String productDescription;
  private boolean active;
  private List<Categories> categories;
}
