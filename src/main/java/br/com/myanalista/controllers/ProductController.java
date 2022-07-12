package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.models.response.ProductSearchResponse;
import br.com.myanalista.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/sku")
    public ResponseEntity<ProductResponse> findProductBySku(@RequestParam Optional<Integer> sku) {
        try {
            return service.findBySku(sku.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Products> findProductById(@RequestParam Optional<Long>  id) {
        return service.findById(id.get());
    }

    @PostMapping
    public ResponseEntity<Products> save(@RequestBody @Valid ProductRequestPost productRequestPost) {
            return service.save(productRequestPost);
    }

    @PutMapping
    public ResponseEntity<Products> update(@RequestBody @Valid ProductRequestPost productRequestPut) {
        try {
            return service.update(productRequestPut);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Optional<Long>  id) {
        try {
            return service.delete(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductSearchResponse>> findAllProductWithSearch(@RequestParam Optional<String>  search, Pageable pageable ) {
        try {
            if(search.isEmpty()){
                service.findAllWithPage(pageable);
            }
            return service.findAllWithPageSeek(search.get().trim(), pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
