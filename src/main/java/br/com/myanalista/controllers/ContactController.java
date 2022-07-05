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
        return service.findById(id);
    }

    @PostMapping
    public ContactResponse save(@RequestBody ContactRequestPost request) {
            return service.save(request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
            return service.delete(id);
    }

    @PutMapping("/{id}")
    public ContactResponse update(@PathVariable(value = "id") Long id,
                                  @RequestBody ContactRequestPut request) {
            return service.update(request);
    }
}
