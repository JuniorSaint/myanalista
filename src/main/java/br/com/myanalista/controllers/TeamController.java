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

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.request.TeamsRequestPost;
import br.com.myanalista.models.request.TeamsRequestPut;
import br.com.myanalista.models.response.TeamsResponse;
import br.com.myanalista.services.TeamsService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/teams")
@AllArgsConstructor

public class TeamController {
    @Autowired
    private TeamsService service;

    @GetMapping("/{id}")
    public TeamsResponse findAllWithListTeams(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public TeamsResponse saveTeams(@RequestBody TeamsRequestPost request) {
        try {
            return service.save(request);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTeams(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public TeamsResponse updateTeams(@PathVariable(value = "id") Long id,
                                     @RequestBody TeamsRequestPut request) {
        try {
            return service.update(request);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}