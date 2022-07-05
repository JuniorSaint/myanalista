package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/customer")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/{id}")
    public Customer findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/page")
    public Page<Customer> findAllWithPage(Pageable page) {
        return service.findAllWithPage(page);
    }
}
