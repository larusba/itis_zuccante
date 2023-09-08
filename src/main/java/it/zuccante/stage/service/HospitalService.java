package it.zuccante.stage.service;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.service.dto.HospitalDTO;
import org.neo4j.driver.internal.value.MapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final Logger log = LoggerFactory.getLogger(HospitalService.class);

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Optional<Hospital> findHospital(HospitalDTO hospitalDTO) {
        return this.hospitalRepository.findOneByName(hospitalDTO.getName());
    }

    public List<Hospital> findAllHospital() {
        return this.hospitalRepository.findAll();
    }
    public List<Hospital> findHospitalByHealthService(List<String> healthServiceStrings) {
        return this.hospitalRepository.findHospitalsByHealthServices(healthServiceStrings);
    }

    public List<MapValue> findNearestHospitalByHealthService(List<String> healthServices, double latitudine, double logitudine) {
        return this.hospitalRepository.findHospitalByShortestDistance(healthServices, latitudine, logitudine);
    }
}

