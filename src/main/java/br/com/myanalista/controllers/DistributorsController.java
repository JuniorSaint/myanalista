package br.com.myanalista.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import br.com.myanalista.models.request.DistributorRequestPost;
import br.com.myanalista.models.request.DistributorRequestPut;
import br.com.myanalista.models.response.DistributorResponse;
import br.com.myanalista.services.DistributorService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/customer")
@AllArgsConstructor

public class DistributorsController {
  @Autowired
  private DistributorService service;

  @GetMapping("/{id}")
  public DistributorResponse findAllWithListCustomer(@PathVariable(value = "id") Long id) {
    DistributorResponse response = service.findById(id);
    return response;
  }

//  @GetMapping("/listSearch")
//  public DistributorResponse findForSearchWithPageable() {
//  Page<DistributorResponse> response = service.listOfDistributor(id);
//    return response;
//  }

  @PostMapping
  public DistributorResponse saveCustomer(@RequestBody  DistributorRequestPost request) {
    try {
      DistributorResponse response = service.save(request);
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
  public DistributorResponse updateCustomer(@PathVariable(value = "id") Long id,
      @RequestBody  DistributorRequestPut request) {
    try {
      DistributorResponse response = service.update(request);
      return response;
    } catch (BusinessException e) {
     throw new BusinessException(e.getMessage());
    }
  }
}