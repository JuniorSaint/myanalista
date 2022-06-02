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
import br.com.myanalista.models.request.CustomerRequestPost;
import br.com.myanalista.models.request.CustomerRequestPut;
import br.com.myanalista.models.response.CustomerResponse;
import br.com.myanalista.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/customer")
@AllArgsConstructor

public class CustomersController {
  @Autowired
  private CustomerService service;

  @GetMapping("/{id}")
  public CustomerResponse findAllWithListCustomer(@PathVariable(value = "id") Long id) {
    CustomerResponse response = service.findById(id);
    return response;
  }

  @PostMapping
  public CustomerResponse saveCustomer(@RequestBody  CustomerRequestPost request) {
    try {
      CustomerResponse response = service.save(request);
      return response;
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public String deleteCustomer(@PathVariable(value = "id") Long id) {
    try {
      return service.delete(id);
    } catch (BusinessException e) {
      throw new BusinessException(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public CustomerResponse updateCustomer(@PathVariable(value = "id") Long id,
      @RequestBody  CustomerRequestPut request) {
    try {
      CustomerResponse response = service.update(request);
      return response;
    } catch (BusinessException e) {
     throw new BusinessException(e.getMessage());
    }
  }
}