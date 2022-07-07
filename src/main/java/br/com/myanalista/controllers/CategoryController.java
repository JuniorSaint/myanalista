package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Categories;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.myanalista.models.response.CategoryResponse;
import br.com.myanalista.services.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/categories")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Categories save(@RequestBody CategoryRequestPost request) {
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
    public Categories update(@PathVariable(value = "id") Long id,
                             @RequestBody CategoryRequestPut request) throws Exception {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
