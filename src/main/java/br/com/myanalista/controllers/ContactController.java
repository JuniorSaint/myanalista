package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.response.ContactSearchResponse;
import br.com.myanalista.models.response.DistributorSearchResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.myanalista.models.request.ContactRequestPost;
import br.com.myanalista.models.request.ContactRequestPut;
import br.com.myanalista.models.response.ContactResponse;
import br.com.myanalista.services.ContactService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/contacts")
@AllArgsConstructor
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping("/{id}")
    public ContactResponse findAllWithList(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
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

    @GetMapping
    public Page<ContactSearchResponse> findForSearchWithPageable(Pageable page) {
        Page<ContactSearchResponse> response = service.listOfContactPageable(page);
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ContactSearchResponse>> findAllWithSearch(@RequestBody Contacts contacts, Pageable pageable) {
        try {
            return service.findAllWithPageSeek(contacts, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
