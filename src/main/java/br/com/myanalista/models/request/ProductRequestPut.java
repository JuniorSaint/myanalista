package br.com.myanalista.models.request;

import javax.validation.constraints.NotNull;

public class ProductRequestPut extends ProductRequestPost {
    @NotNull(message = "Id is a mandatory field.")
    private Long id;
}
