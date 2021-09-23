package com.larus.itiszuccante.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.domain.User;
import com.larus.itiszuccante.repository.ProfileRepository;
import com.larus.itiszuccante.repository.UserRepository;
import com.larus.itiszuccante.service.ProfileService;

@Service
public class DefaultProfileService implements ProfileService {

	@Autowired
	private ProfileRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Profile create(String userId, Profile p) {
		User user = userRepository.findById(userId).orElseThrow();
		user.setProfile(p);
		userRepository.save(user);
		return repository.save(p);
	}

	@Override
	public Optional<Profile> read(String id) {
		return repository.findById(id);
	}

	@Override
	public Profile update(Profile p) {
		return repository.save(p);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

}