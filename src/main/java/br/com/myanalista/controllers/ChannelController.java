package br.com.myanalista.controllers;

import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.services.ChannelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/channel")
@AllArgsConstructor
public class ChannelController {

    @Autowired
    private ChannelService service;

    @GetMapping("/{id}")
    public ChannelResponse findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/page")
    public Page<ChannelResponse> findAllWithPage(Pageable page) {
        return service.findAllWithPage(page);
    }
}
