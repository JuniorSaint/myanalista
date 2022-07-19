package br.com.myanalista.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.request.ContactRequestPost;
import br.com.myanalista.models.request.ContactRequestPut;
import br.com.myanalista.models.response.ContactResponse;
import br.com.myanalista.models.response.ContactSearchResponse;
import br.com.myanalista.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/contacts")
@AllArgsConstructor
@Tag(name = "Contact", description = "Manager contact")
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping
    public ContactResponse findAllContactWithList(@RequestParam Optional<Long> id) {
        try {
            return service.findById(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ContactResponse save(@RequestBody ContactRequestPost request) {
        try {
            return service.save(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Optional<Long> id) {
        try {
            return service.delete(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ContactResponse update(@RequestBody ContactRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ContactSearchResponse>> findAllContactWithSearch(@RequestParam Optional<String> search, Pageable pageable) {
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
