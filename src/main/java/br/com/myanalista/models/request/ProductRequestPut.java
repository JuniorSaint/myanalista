package br.com.myanalista.models.request;

import br.com.myanalista.models.entities.Categories;

import javax.validation.constraints.NotNull;

public class ProductRequestPut extends ProductRequestPost {
    @NotNull(message = "Id is a mandatory field.")
    private Long id;
}
