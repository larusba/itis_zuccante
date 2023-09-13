package it.zuccante.stage.service;

import it.zuccante.stage.domain.Intervention;
import it.zuccante.stage.repository.InterventionRepository;
import it.zuccante.stage.service.dto.InterventionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterventionService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final InterventionRepository interventionRepository;

    public InterventionService(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }
    public Optional<Intervention> createIntervetion(InterventionDTO interventionDTO){
        String ospedale = interventionDTO.getNomeOspedale();
        String prestazione = interventionDTO.getNomePrestazione();
        String nomePaziente = interventionDTO.getNomePaziente();
        String cognomePaziente = interventionDTO.getCognomePaziente();
        String numeroAmbulanza = interventionDTO.getNumeroAmbulanza();
        String luogoIntervento = interventionDTO.getLuogoIntervento();
        double latitudine = interventionDTO.getLatitude();
        double longitudine = interventionDTO.getLongitude();
        double tempoPercorrenza = interventionDTO.getTempoPercorrenza();
        return interventionRepository.createIntervetion(ospedale, prestazione, nomePaziente, cognomePaziente,
            numeroAmbulanza, luogoIntervento, latitudine, longitudine, tempoPercorrenza);
    }
}
