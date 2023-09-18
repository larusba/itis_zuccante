package it.zuccante.stage.web.rest;


import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.service.HospitalService;

import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.HospitalIntervetionDTO;
import it.zuccante.stage.service.dto.TrackDTO;

import org.neo4j.driver.internal.value.MapValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HospitalResource {

    private final HospitalService hospitalService;

    public HospitalResource(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("/hospitalByName")
    public Optional<Hospital> findHospital(HospitalDTO name){
        return hospitalService.findHospital(name);
    }

    @GetMapping("/hospitalAll")
    public List<Hospital> getServiceList() {
        return hospitalService.findAllHospital();
    }

    @GetMapping("/find-Hospital-By-Health-Services")
    public List<Hospital> findHospitalByHealthService(@RequestParam List<String> healthServicesNames){
        return hospitalService.findHospitalByHealthService(healthServicesNames);
    }

    @GetMapping("/findNearestHospitalByHealthService")
    public TrackDTO findNearestHospitalByHealthService(@RequestParam List<String> healthServices, double latitudine, double longitudine ){
        return hospitalService.findNearestHospitalByHealthService(healthServices, latitudine, longitudine);
    }

    @GetMapping("/FindNumberOfInterventionByHospitalAll")
    public List<HospitalIntervetionDTO>FindNumberOfInterventionByHospitalAll(){
        return hospitalService.FindNumberOfInterventionByHospitalAll();
    }
}
