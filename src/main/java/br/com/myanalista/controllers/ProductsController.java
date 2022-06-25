package br.com.myanalista.controllers;


import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.repositories.CategoryRepository;
import br.com.myanalista.repositories.ProductRepository;
import br.com.myanalista.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/products")
@AllArgsConstructor
public class ProductsController {

    @Autowired
    private ProductService service;

    @GetMapping("/{sku}")
    public ProductResponse findProductBySku(@PathVariable(value = "sku") String sku){
        ProductResponse product = service.findBySku(sku);
        return product;
    }
}
