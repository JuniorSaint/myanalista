package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.response.DistributorSearchResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.myanalista.models.request.DistributorRequestPost;
import br.com.myanalista.models.request.DistributorRequestPut;
import br.com.myanalista.models.response.DistributorResponse;
import br.com.myanalista.services.DistributorService;

import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/distributor")
@AllArgsConstructor
public class DistributorController {
    @Autowired
    private DistributorService service;

    @GetMapping
    public ResponseEntity<DistributorResponse> findById(@RequestParam Optional<Long> id) {
        try {
            return service.findById(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<DistributorResponse> saveCustomer(@RequestBody DistributorRequestPost request) {
        try {
            return service.save(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCustomer(@RequestParam Optional<Long> id) {
        try {
            return service.delete(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<DistributorResponse> updateCustomer(@RequestBody DistributorRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DistributorSearchResponse>> findAllDistributorWithSearch(@RequestParam Optional<String> search, Pageable pageable) {
        try {
            if (search.isEmpty()) {
                return service.findAllWithPage(pageable);
            }
            return service.findAllWithPageSeek(search.get().trim(), pageable);
        } catch (NoSuchElementException e) {
            return service.findAllWithPage(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}