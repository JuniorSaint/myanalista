package br.com.myanalista.models.response;

import java.util.Set;

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
public class CategoryResponse {
  private Long id;
  private String categoryName;
  private Set<Categories> categories;
  private Categories category;
}
