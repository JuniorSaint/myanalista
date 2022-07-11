package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.response.ProductResponse;
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

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
@Tag(name = "Product", description = "Implement the products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponse> findProductBySku(@PathVariable(value = "sku") Integer sku) {
        try {
            return service.findBySku(sku);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> findProductById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Products> save(@RequestBody @Valid ProductRequestPost productRequestPost) {
            return service.save(productRequestPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductRequestPost productRequestPut) {
        try {
            return service.update(productRequestPut);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAllWithList(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Products>> findAllWithSearch(@RequestBody Products products, Pageable pageable ) {
        try {
            return service.findAllWithPageSeek(products, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
