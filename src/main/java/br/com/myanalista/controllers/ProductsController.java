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
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.request.ProductRequestPut;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/products")
@AllArgsConstructor
public class ProductsController {
  
  @Autowired
  private ProductService service;

  @GetMapping("/{id}")
  public ProductResponse findAllWithList(@PathVariable(value = "id") String id) {
    ProductResponse response = service.findById(id);
    return response;
  }

  @PostMapping
  public ProductResponse save(@RequestBody  ProductRequestPost request) {
    try {
      ProductResponse response = service.save(request);
      return response;
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable(value = "id") String id) {
    try {
      return service.delete(id);
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ProductResponse update(@PathVariable(value = "id") Long id,
      @RequestBody ProductRequestPut request) {
    try {
      ProductResponse response = service.update(request);
      return response;
    } catch (BusinessException e) {
     throw new BusinessException(e.getMessage());
    }
  }
}
