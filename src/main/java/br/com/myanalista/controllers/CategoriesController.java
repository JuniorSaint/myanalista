package br.com.myanalista.controllers;

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

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.request.CategoryRequestPost;
import br.com.myanalista.models.request.CategoryRequestPut;
import br.com.myanalista.models.response.CategoryResponse;
import br.com.myanalista.services.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/categories")
@AllArgsConstructor
public class CategoriesController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequestPost request) {
        try {
            return service.save(request);
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

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable(value = "id") Long id,
                                   @RequestBody CategoryRequestPut request) {
        try {
            return service.update(request);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
