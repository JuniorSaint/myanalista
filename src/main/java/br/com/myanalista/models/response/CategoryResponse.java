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
public class CategoryResponse extends RepresentationModel<CategoryResponse> {
  private Long id;
  private String name;
  private List<Categories> category;
}
