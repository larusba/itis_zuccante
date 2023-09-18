package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.Intervention;
import it.zuccante.stage.service.InterventionService;
import it.zuccante.stage.service.dto.InterventionDTO;
import it.zuccante.stage.web.rest.errors.BadHospitalOrHealthServiceNameException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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



    @PostMapping("/createIntervention")
    public ResponseEntity<List<Intervention>> createIntervetion(@RequestBody InterventionDTO interventionDTO){
        List<Intervention> intervention = interventionService.createIntervetion(interventionDTO);
        if(intervention.isEmpty()) {
            throw new BadHospitalOrHealthServiceNameException();
        }
        return ResponseEntity.ok().body(intervention);
    }

}
