package br.com.myanalista.models.request;

import javax.validation.constraints.NotEmpty;
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
public class CategoryRequestPut extends CategoryRequestPost{
  @NotEmpty(message = "Id is mandatory field")
  private Long id;
}
