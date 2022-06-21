package br.com.myanalista.models.request;

import java.util.List;

import br.com.myanalista.models.entities.ProductCategory;
import br.com.myanalista.models.entities.SellOut;
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

  private String sku;
  private String productDescription;
  private boolean active;
  private List<SellOut> sellOuts;
  private List<ProductCategory> categories;
}
