package br.com.myanalista.controllers;

import br.com.myanalista.models.request.TeamsRequestPost;
import br.com.myanalista.models.request.TeamsRequestPut;
import br.com.myanalista.models.response.TeamsResponse;
import br.com.myanalista.models.response.TeamsSearchResponse;
import br.com.myanalista.services.TeamsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/teams")
@AllArgsConstructor
@Tag(name = "Teams", description = "Manager teams")
public class TeamController {
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

    @DeleteMapping
    public ResponseEntity<Object> deleteTeams(@RequestParam Optional<Long> id) {
        try {
            return service.delete(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<TeamsResponse> updateTeams(@RequestBody TeamsRequestPut request) {
        try {
            return service.update(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TeamsSearchResponse>> findAllTeamsWithSearch(@RequestParam Optional<String> search, Pageable pageable) {
        try {
            if(search.isEmpty()){
                return service.listOfTeams(pageable);
            }
            return service.findAllWithPageSeek(search.get(), pageable);
        } catch (NoSuchElementException e) {
            return service.listOfTeams(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
