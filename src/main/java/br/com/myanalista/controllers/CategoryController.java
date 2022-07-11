package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.response.CategoryMainResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.request.CategoryRequestPost;
import br.com.myanalista.models.request.CategoryRequestPut;
import br.com.myanalista.services.CategoryService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/categories")
@AllArgsConstructor
@Tag(name = "Category", description = "Implement the categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categories> findById(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Categories> save(@RequestBody CategoryRequestPost request) {
        try {
            return service.save(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            return service.delete(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(@PathVariable(value = "id") Long id,
                                             @RequestBody CategoryRequestPut request) throws Exception {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Categories>> findAllWithSearch(@RequestBody Categories categories, Pageable pageable ) {
        try {
            return service.findAllWithPageSeek(categories, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/main-categories")
    public ResponseEntity<List<CategoryMainResponse>> mainCategories( ) {
        try {
            return service.listCategoryMain();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
