package it.zuccante.stage.service;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.TrackDTO;
import org.neo4j.driver.internal.value.MapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<TrackDTO> findNearestHospitalByHealthService(List<String> healthServices, double latitudine, double logitudine) {
        List<MapValue> mapValue = this.hospitalRepository.findHospitalByShortestDistance(healthServices, latitudine, logitudine);
        List<TrackDTO> track = new ArrayList<>();
        track.add(new TrackDTO("",0,0,""));
        track.get(0).setHospitalName(mapValue.get(0).get("hospital", ""));
        track.get(0).setDuration(mapValue.get(0).get("duration", 0));
        track.get(0).setDistance(mapValue.get(0).get("distance", 0d));
        track.get(0).setCongestion(mapValue.get(0).get("congestion", ""));
        return track;
    }
}

