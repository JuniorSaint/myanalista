package br.com.myanalista.controllers;


import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.response.ChannelResponse;
import br.com.myanalista.models.response.ClusterResponse;
import br.com.myanalista.services.ChannelService;
import br.com.myanalista.services.ClusterGecService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @GetMapping("/pageable")
    public Page<ClusterResponse> findAllClusterWithPage(Pageable page) {
        try {
            return service.findAllWithPage(page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}