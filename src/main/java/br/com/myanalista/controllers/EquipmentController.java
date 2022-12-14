package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.services.EquipmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/equipment")
@AllArgsConstructor
@Tag(name = "Equipment", description = "Manager equipment")
public class EquipmentController {
    private EquipmentService service;

    @GetMapping
    public ResponseEntity<Equipment> findById(@RequestParam Optional<Long> id) {
        try {
            return service.findById(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<Equipment>> findAllEquipmentWithPage(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
