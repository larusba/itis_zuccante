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
        String ospedale = interventionDTO.getNomeHospedale();
        String prestazione = interventionDTO.getNomePrestazione();
        String nomePaziente = interventionDTO.getName();
        String cognomePaziente = interventionDTO.getCognome();
        String numeroAmbulanza = interventionDTO.getNumeroAmbulanza();
        String luogoIntervento = interventionDTO.getLuogoIntervento();
        double latitudine = interventionDTO.getLatitude();
        double longitudine = interventionDTO.getLongitude();
        int tempoPercorrenza = 1234;
        return interventionRepository.createIntervetion(ospedale, prestazione, nomePaziente, cognomePaziente, numeroAmbulanza, luogoIntervento, latitudine, longitudine, tempoPercorrenza);
    }
}
