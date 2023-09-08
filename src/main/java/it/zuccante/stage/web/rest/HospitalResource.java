package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.service.HospitalService;
import it.zuccante.stage.service.dto.HospitalDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HospitalResource {

    private final HospitalService hospitalService;

    public HospitalResource(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("/hospitalServices")
    public Optional<Hospital> findHospital(HospitalDTO name){
        return hospitalService.findHospital(name);
    }
}
