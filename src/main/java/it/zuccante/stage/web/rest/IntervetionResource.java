package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.Intervetion;
import it.zuccante.stage.service.IntervetionService;
import it.zuccante.stage.service.dto.HealthServiceDTO;
import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.IntervetionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IntervetionResource {

    private final IntervetionService intervetionService;

    public IntervetionResource(IntervetionService intervetionService) {
        this.intervetionService = intervetionService;
    }

    @PostMapping("/createIntervetion")
    public Intervetion createIntervetion(IntervetionDTO intervetionDTO){
        return intervetionService.createIntervetion(intervetionDTO);

    }

}
