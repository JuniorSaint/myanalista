package br.com.myanalista.controllers;


import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
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
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/sku/{sku}")
    public ProductResponse findProductBySku(@PathVariable(value = "sku") Integer sku) {
        return service.findBySku(sku);
    }

    @GetMapping("/{id}")
    public Products findProductById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Products save(@RequestBody @Valid ProductRequestPost productRequestPost) {
        try {
            return service.save(productRequestPost);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Products update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequestPost productRequestPut) {
        try {
            return service.update(productRequestPut);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @GetMapping("/page")
    public Page<ProductResponse> findAllWithList(Pageable page) {
        return service.findAllWithPage(page);
    }

}
