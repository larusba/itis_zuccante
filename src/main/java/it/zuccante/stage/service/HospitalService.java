package it.zuccante.stage.service;

import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.HospitalIntervetionDTO;
import it.zuccante.stage.service.dto.TrackDTO;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.MapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

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
            hospitalIntervetionDTO.setCountIntervention(interventi);
            results.add(hospitalIntervetionDTO);
        }
        return results;
    }

    public List<TrackDTO> findNearestHospitalByHealthService(List<String> healthServices, double latitudine, double logitudine) {
        List<MapValue> mapValue = this.hospitalRepository.findHospitalByShortestDistance(healthServices, latitudine, logitudine);
        List<TrackDTO> tracklList = new ArrayList<>();
        double distMin = mapValue.get(0).get("distance", 42_000d);

        for (int y = 0; y < mapValue.size(); y++) {
            TrackDTO track = new TrackDTO();
            track.setHospitalName(mapValue.get(y).get("hospital", ""));
            track.setDuration(mapValue.get(y).get("duration", 0));
            track.setDistance(mapValue.get(y).get("distance", 0d));
            track.setCongestion(mapValue.get(y).get("congestion", ""));
            track.setLongitude(mapValue.get(y).get("lon").asDouble());
            track.setLatitude(mapValue.get(y).get("lat").asDouble());
            tracklList.add(track);
        }

        Collections.sort(tracklList, new Comparator<TrackDTO>() {
            @Override
            public int compare(TrackDTO o1, TrackDTO o2) {
                return  o1.getDuration() - o2.getDuration();
            }
        });

        return  tracklList;

    }


}

