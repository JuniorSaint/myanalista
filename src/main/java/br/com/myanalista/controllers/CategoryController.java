package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.response.CategoryMainResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myanalista.models.request.CategoryRequestPost;
import br.com.myanalista.models.request.CategoryRequestPut;
import br.com.myanalista.services.CategoryService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/categories")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Categories> findById(@RequestParam Optional<Long> id) {
        try {
            return service.findById(id.get());
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

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Optional<Long>  id) throws Exception {
        try {
            return service.delete(id.get());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Categories> update(@RequestBody CategoryRequestPut request) throws Exception {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/search/")
    public ResponseEntity<Page<Categories>> findAllWithSearch(@RequestParam Optional<String>  search, Pageable pageable) {
        try {
            if(search.isEmpty()){
                return service.findAllWithPage( pageable);
            }
            return service.findAllWithPageSeek(search.get().trim(), pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/main-categories")
    public ResponseEntity<List<CategoryMainResponse>> mainCategories() {
        try {
            return service.listCategoryMain();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
