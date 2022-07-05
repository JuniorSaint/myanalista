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

import br.com.myanalista.models.request.DistributorRequestPost;
import br.com.myanalista.models.request.DistributorRequestPut;
import br.com.myanalista.models.response.DistributorResponse;
import br.com.myanalista.services.DistributorService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/distributor")
@AllArgsConstructor

public class DistributorController {
    @Autowired
    private DistributorService service;

    @GetMapping("/{id}")
    public DistributorResponse findAllWithListCustomer(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

//  @GetMapping("/listSearch")
//  public DistributorResponse findForSearchWithPageable() {
//  Page<DistributorResponse> response = service.listOfDistributor(id);
//    return response;
//  }

    @PostMapping
    public DistributorResponse saveCustomer(@RequestBody DistributorRequestPost request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @PutMapping("/{id}")
    public DistributorResponse updateCustomer(@PathVariable(value = "id") Long id,
                                              @RequestBody DistributorRequestPut request) {
        return service.update(request);
    }
}