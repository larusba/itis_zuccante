package it.zuccante.stage.service;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.repository.HealthServiceRepository;
import it.zuccante.stage.service.dto.HospitalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthServiceService {
    private final Logger log = LoggerFactory.getLogger(HealthServiceService.class);

    private final HealthServiceRepository healthServiceRepository;

    public HealthServiceService(HealthServiceRepository healthServiceRepository) {
        this.healthServiceRepository = healthServiceRepository;
    }

    public List<HealthService> findHospital(HospitalDTO hospitalDTO) {
        return this.healthServiceRepository.findAll();
    }

}
