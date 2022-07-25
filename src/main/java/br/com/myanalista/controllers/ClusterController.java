package br.com.myanalista.controllers;


import br.com.myanalista.models.response.ClusterResponse;
import br.com.myanalista.services.ClusterGecService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/cluster")
@AllArgsConstructor
@Tag(name = "Cluster", description = "Manager cluster")
public class ClusterController {
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