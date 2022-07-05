package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.services.LendingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/lending")
@AllArgsConstructor
public class LendingController {
    @Autowired
    private LendingService serviceLending;

    @GetMapping("/{id}")
    public Lending findProductById(@PathVariable(value = "id") Long id) {
        return serviceLending.findById(id);
    }

    @GetMapping("/page")
    public Page<Lending> findAll(Pageable pageable) {
        return serviceLending.findAll(pageable);
    }
}
