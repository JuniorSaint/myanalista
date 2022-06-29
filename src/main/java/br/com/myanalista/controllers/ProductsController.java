package br.com.myanalista.controllers;


import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.request.ProductRequestPut;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductsController {

    @Autowired
    private ProductService service;

    @GetMapping("/sku/{sku}")
    public ProductResponse findProductBySku(@PathVariable(value = "sku") Integer sku) {
        ProductResponse product = service.findBySku(sku);
        return product;
    }

    @GetMapping("/{id}")
    public Products findProductById(@PathVariable(value = "id") Long id) {
        Products product = service.findById(id);
        return product;
    }

    @PostMapping
    public ProductResponse save(@RequestBody @Valid ProductRequestPost productRequestPost) {
        try {
            ProductResponse responseProduct = service.save(productRequestPost);
            return responseProduct;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/id")
    public ProductResponse update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequestPut productRequestPut) {
        try {
            ProductResponse responseProduct = service.update(productRequestPut);
            return responseProduct;
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/id")
    public String delete(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/page/{page}")
    public Page<ProductResponse> findAllWithList(@PathVariable(value = "page") Pageable page) {
        Page<ProductResponse> response = service.findAllWithPage(page);
        return response;
    }

}
