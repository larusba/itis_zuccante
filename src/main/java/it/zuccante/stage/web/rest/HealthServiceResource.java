package it.zuccante.stage.web.rest;

import it.zuccante.stage.domain.HealthService;
import it.zuccante.stage.domain.User;
import it.zuccante.stage.repository.HealthServiceRepository;
import it.zuccante.stage.repository.UserRepository;
import it.zuccante.stage.security.AuthoritiesConstants;
import it.zuccante.stage.service.HealthServiceService;
import it.zuccante.stage.service.UserService;
import it.zuccante.stage.service.dto.AdminUserDTO;
import it.zuccante.stage.web.rest.errors.EmailAlreadyUsedException;
import it.zuccante.stage.web.rest.errors.LoginAlreadyUsedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthService")
public class HealthServiceResource {
    private final HealthServiceService healtService;
    private final  HealthServiceRepository healthServiceRepository;

    public HealthServiceResource(HealthServiceService healtService, HealthServiceRepository healthServiceRepository) {
        this.healtService = healtService;
        this.healthServiceRepository = healthServiceRepository;
    }
    @PutMapping("/118")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<HealthService> getServiceList(@Valid @RequestBody HealthService healthService) {
        return null;
    }
}
