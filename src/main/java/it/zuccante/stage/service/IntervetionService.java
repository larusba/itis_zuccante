package it.zuccante.stage.service;

import it.zuccante.stage.domain.Hospital;
import it.zuccante.stage.domain.Intervetion;
import it.zuccante.stage.repository.HospitalRepository;
import it.zuccante.stage.repository.IntervetionRepository;
import it.zuccante.stage.repository.UserRepository;
import it.zuccante.stage.service.dto.HealthServiceDTO;
import it.zuccante.stage.service.dto.HospitalDTO;
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
    public Intervetion createIntervetion(Intervetion intervetion, HospitalDTO hospitalDTO, HealthServiceDTO healthServiceDTO){
        String ospedale = hospitalDTO.getName();
        String prestazione = healthServiceDTO.getName();
        String nomePaziente = intervetion.getName();
        String cognomePaziente = intervetion.getCognome();
        String numeroAmbulanza = intervetion.getNumeroAmbulanza();
        String luogoIntervento = intervetion.getLuogoIntervento();
        double latitudine = intervetion.getLatitude();
        double longitudine = intervetion.getLongitude();
        int tempoPercorrenza = 1234;
        Intervetion intervetion1 = intervetionRepository.createIntervetion(ospedale, prestazione, nomePaziente, cognomePaziente, numeroAmbulanza, luogoIntervento, latitudine, longitudine, tempoPercorrenza);
        return intervetion1;
    }
}
