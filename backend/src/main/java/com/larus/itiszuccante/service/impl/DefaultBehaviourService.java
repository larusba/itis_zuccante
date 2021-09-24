package com.larus.itiszuccante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Behaviour;
import com.larus.itiszuccante.domain.BehaviourType;
import com.larus.itiszuccante.domain.FuelConsumption;
import com.larus.itiszuccante.domain.FuelType;
import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.domain.Vehicle;
import com.larus.itiszuccante.repository.BehaviourRepository;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.service.BehaviourService;

import java.security.Principal;
import java.util.Optional;

@Service
public class DefaultBehaviourService implements BehaviourService {

    @Autowired
    private BehaviourRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Behaviour create(String userId, Behaviour behaviour) {
    	behaviour.setEmission(behaviour.getType() == BehaviourType.CAR_TRIP || behaviour.getType() == BehaviourType.FLIGHT?calculateEmissions(behaviour):0);
    	User user = userRepository.findById(userId).orElseThrow();
    	user.addBehaviour(behaviour);
    	userRepository.save(user);
        return repository.save(behaviour);
    }

    private double calculateEmissions(Behaviour behaviour) {
    	BehaviourType type = behaviour.getType();
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findOneByLogin(principal.getName()).orElseThrow();
		Profile profile = user.getProfile();
		switch(type) {
		
			case CAR_TRIP:
				Vehicle vehicle = profile.getVehicle();
				double fuelEmission;
				if (vehicle.getFuelType() != FuelType.ELECTRIC && vehicle.getFuelType() != FuelType.PLUG_IN_HYBRID)
					fuelEmission = vehicle.getFuelType().getEmission().get("default");
				else fuelEmission = vehicle.getFuelType().getEmission().get(behaviour.getElectricLocation().toString());
				return behaviour.getDistance() * FuelConsumption.MAP.get(vehicle.getFuelType()).get(vehicle.getCarType()) * fuelEmission;
				
			default:
				return 0;
		}
	
	}

	@Override
    public Optional<Behaviour> read(String id) {
        return repository.findById(id);
    }

    @Override
    public Behaviour update(Behaviour behaviour) {
        return repository.save(behaviour);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}
