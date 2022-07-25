package br.com.myanalista.models.response;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProductSearchResponse extends RepresentationModel<ProductSearchResponse> {
    private Long id;
    private String sku;
    private String productDescription;
    private boolean active;
    private List<CategoryMainResponse> categories;
}
