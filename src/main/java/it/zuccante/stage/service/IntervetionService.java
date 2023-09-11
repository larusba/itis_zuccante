package it.zuccante.stage.service;

import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.domain.Intervetion;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.repository.IntervetionRepository;
import it.zuccante.stage.repository.UserRepository;
import it.zuccante.stage.service.dto.HealthServiceDTO;
import it.zuccante.stage.service.dto.HospitalDTO;
import it.zuccante.stage.service.dto.IntervetionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IntervetionService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final IntervetionRepository intervetionRepository;

    public IntervetionService(IntervetionRepository intervetionRepository) {
        this.intervetionRepository = intervetionRepository;
    }
    public Intervetion createIntervetion(IntervetionDTO intervetionDTO){
        String ospedale = intervetionDTO.getNomeHospedale();
        String prestazione = intervetionDTO.getNomePrestazione();
        String nomePaziente = intervetionDTO.getName();
        String cognomePaziente = intervetionDTO.getCognome();
        String numeroAmbulanza = intervetionDTO.getNumeroAmbulanza();
        String luogoIntervento = intervetionDTO.getLuogoIntervento();
        double latitudine = intervetionDTO.getLatitude();
        double longitudine = intervetionDTO.getLongitude();
        int tempoPercorrenza = 1234;
        Intervetion intervetion1 = intervetionRepository.createIntervetion(ospedale, prestazione, nomePaziente, cognomePaziente, numeroAmbulanza, luogoIntervento, latitudine, longitudine, tempoPercorrenza);
        return intervetion1;
    }
}
