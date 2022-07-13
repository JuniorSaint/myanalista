package br.com.myanalista.models.request;

import javax.validation.constraints.NotEmpty;

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
public class CategoryRequestPost {
    @NotEmpty(message = "Name of the category is mandatory.")
    private String name;
    private Categories category;
}
