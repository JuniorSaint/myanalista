package br.com.myanalista.controllers;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.models.response.ContactSearchResponse;
import br.com.myanalista.models.response.DistributorSearchResponse;
import br.com.myanalista.models.response.TeamsSearchResponse;
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
    public ResponseEntity<TeamsResponse> findById(@PathVariable(value = "id") Long id) {
        try {
            return service.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<TeamsResponse> saveTeams(@RequestBody TeamsRequestPost request) {
        try {
            return service.save(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeams(@PathVariable(value = "id") Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamsResponse> updateTeams(@PathVariable(value = "id") Long id,
                                                     @RequestBody TeamsRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Page<TeamsSearchResponse>> findForSearchWithPageable(Pageable page) {
        try {
            return service.listOfTeams(page);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<Page<TeamsSearchResponse>> findAllWithSearch(@RequestBody Teams teams, Pageable pageable ) {
        try {
            return service.findAllWithPageSeek(teams, pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
