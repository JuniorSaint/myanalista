package br.com.myanalista.controllers;

import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.services.ChannelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/channel")
@AllArgsConstructor
public class ChannelController {
    @Autowired
    private ChannelService service;
    @GetMapping
    public ChannelResponse findById(@RequestParam Optional<Long> id) {
        try {
            return service.findById(id.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public Page<ChannelResponse> findAllWithPage(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
