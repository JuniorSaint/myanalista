package br.com.myanalista.controllers;


import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.models.response.ClusterResponse;
import br.com.myanalista.services.ChannelService;
import br.com.myanalista.services.ClusterGecService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/cluster")
@AllArgsConstructor
public class ClusterController {

    @Autowired
    private ClusterGecService service;
    @GetMapping("/page")
    public Page<ClusterResponse> findAllWithPage(Pageable page) {
        return service.findAllWithPage(page);
    }
}