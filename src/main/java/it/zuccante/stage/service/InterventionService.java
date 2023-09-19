package it.zuccante.stage.service;

import it.zuccante.stage.domain.Intervention;
import it.zuccante.stage.repository.InterventionRepository;
import it.zuccante.stage.service.dto.InterventionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterventionService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final InterventionRepository interventionRepository;

    public InterventionService(InterventionRepository interventionRepository) {
        this.interventionRepository = interventionRepository;
    }
    public List<Intervention> createIntervetion(InterventionDTO interventionDTO){
        String ospedale = interventionDTO.getNomeOspedale();
        List<String> prestazione = new ArrayList<>(interventionDTO.getNomePrestazione());
        String nomePaziente = interventionDTO.getNomePaziente();
        String cognomePaziente = interventionDTO.getCognomePaziente();
        String numeroAmbulanza = interventionDTO.getNumeroAmbulanza();
        String luogoIntervento = interventionDTO.getLuogoIntervento();
        double latitudine = interventionDTO.getLatitude();
        double longitudine = interventionDTO.getLongitude();
        double tempoPercorrenza = interventionDTO.getTempoPercorrenza();
        List<Intervention> opt = new ArrayList<>();
        for (int i = 0; i < prestazione.size(); i++){
            Optional<Intervention> intervetion = interventionRepository.createIntervetion(ospedale, prestazione.get(i), nomePaziente, cognomePaziente,
                numeroAmbulanza, luogoIntervento, latitudine, longitudine, tempoPercorrenza);
            intervetion.ifPresent(opt::add);
        }
        return opt;
    }

    public List<Intervention> findAll(){
        return interventionRepository.findAll();
    }

    public List<Intervention> findInterventionsByHospitalName(String hospitalName){
        return interventionRepository.findInterventionsByHospitalName(hospitalName);
    }
}
