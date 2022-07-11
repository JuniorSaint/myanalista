package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.services.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/equipment")
@AllArgsConstructor
@Tag(name = "Equipment", description = "Implement the equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Equipment>> findAllWithPage(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
