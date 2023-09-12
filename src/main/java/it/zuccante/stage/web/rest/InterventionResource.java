package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.Intervention;
import it.zuccante.stage.service.InterventionService;
import it.zuccante.stage.service.dto.InterventionDTO;
import it.zuccante.stage.web.rest.errors.BadHospitalOrHealthServiceNameException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InterventionResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterventionService interventionService;

    public InterventionResource(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @PostMapping("/createIntervetion")
    public ResponseEntity<Intervention> createIntervetion(@RequestBody InterventionDTO interventionDTO){
        Optional<Intervention> intervention = interventionService.createIntervetion(interventionDTO);
        return intervention.map(intervetion -> ResponseEntity.ok().body(intervetion)).orElseThrow(BadHospitalOrHealthServiceNameException::new);
    }

}
