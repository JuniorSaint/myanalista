package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.services.LendingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/lending")
@AllArgsConstructor
@Tag(name = "Lending", description = "Manager lending")
public class LendingController {
    private LendingService serviceLending;
    @GetMapping
    public ResponseEntity<Lending> findProductById(@RequestParam Optional<Long> id) {
        try {
            return serviceLending.findById(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Lending>> findAll(Pageable pageable) {
        try {
            return serviceLending.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
