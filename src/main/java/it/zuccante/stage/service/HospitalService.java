package it.zuccante.stage.service;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.service.dto.HealthServiceDTO;
import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.HospitalIntervetionDTO;
import it.zuccante.stage.service.dto.TrackDTO;
import org.neo4j.driver.Value;
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

    public List<HospitalIntervetionDTO> FindNumberOfInterventionByHospitalAll() {
        List<MapValue> mapValue = this.hospitalRepository.FindNumberOfInterventionByHospitalAll();
        List<HospitalIntervetionDTO> results = new ArrayList<>();
        for (int i = 0; i < mapValue.size(); i++) {
            Value ospedale = mapValue.get(i).get("ospedale");
            int id = ospedale.get("id", 1);
            String address = ospedale.get("indirizzo", "");
            String name = ospedale.get("nome", "");
            double latitude = ospedale.get("latitudine", 0d);
            double longitude = ospedale.get("longitudine", 0d);
            HospitalDTO hospital = new HospitalDTO(id, name, address, latitude, longitude);
            int interventi = mapValue.get(i).get("interventi", 0);
            HospitalIntervetionDTO hospitalIntervetionDTO = new HospitalIntervetionDTO();
            hospitalIntervetionDTO.setHospital(hospital);
            hospitalIntervetionDTO.setCountIntervetion(interventi);
            results.add(hospitalIntervetionDTO);
        }
        return results;
    }

    public TrackDTO findNearestHospitalByHealthService(List<String> healthServices, double latitudine, double logitudine) {
        List<MapValue> mapValue = this.hospitalRepository.findHospitalByShortestDistance(healthServices, latitudine, logitudine);
        double distMin = mapValue.get(0).get("distance", 42_000d);
        int y = 0;
        for (int i = 0; i < mapValue.size(); i++) {
            if (distMin > mapValue.get(i).get("distance", 42_000d)){

                distMin = mapValue.get(i).get("distance", 42_000d);
                y = i;
            }
        }
        TrackDTO track = new TrackDTO();
        track.setHospitalName(mapValue.get(y).get("hospital", ""));
        track.setDuration(mapValue.get(y).get("duration", 0d));
        track.setDistance(mapValue.get(y).get("distance", 0d));
        track.setCongestion(mapValue.get(y).get("congestion", ""));
        return track;
    }
}

