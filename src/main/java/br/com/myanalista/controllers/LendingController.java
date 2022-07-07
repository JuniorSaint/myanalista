package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.services.LendingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Lending> findProductById(@PathVariable(value = "id") Long id) {
        try {
            return serviceLending.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Lending>> findAll(Pageable pageable) {
        try {
            return serviceLending.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
