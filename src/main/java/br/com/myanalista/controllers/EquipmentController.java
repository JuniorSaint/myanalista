package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.models.response.EquipmentResponse;
import br.com.myanalista.services.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/equipment")
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @GetMapping("/{id}")
    public Equipment findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/page")
    public Page<Equipment> findAllWithPage(Pageable page) {
        return service.findAllWithPage(page);
    }
}
