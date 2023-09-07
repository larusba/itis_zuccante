package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.service.HealthServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HealthServiceResource {
    private final HealthServiceService healthService;

    public HealthServiceResource(HealthServiceService healthService) {
        this.healthService = healthService;
    }
    @GetMapping("/healthServices")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<HealthService> getServiceList() {
        return healthService.findAll();
    }
}
