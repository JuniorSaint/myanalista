package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Categories;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CategoryResponse findById(@PathVariable(value = "id") Long id){
    return service.findById(id);
    }
    @PostMapping
    public Categories save(@RequestBody CategoryRequestPost request) {
            return service.save(request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
            return service.delete(id);
    }

    @PutMapping("/{id}")
    public Categories update(@PathVariable(value = "id") Long id,
                                   @RequestBody CategoryRequestPut request) {
            return service.update(request);
    }
}
