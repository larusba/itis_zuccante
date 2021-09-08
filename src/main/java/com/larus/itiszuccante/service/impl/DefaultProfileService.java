package com.larus.itiszuccante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larus.itiszuccante.domain.Profile;
import com.larus.itiszuccante.repository.ProfileRepository;
import com.larus.itiszuccante.service.ProfileService;

@Service
public class DefaultProfileService implements ProfileService {
	
	@Autowired
	private ProfileRepository repository;

	@Override
	public Profile create(Profile p) {
		return repository.save(p);
	}

	@Override
	public Profile read(String id) {
		return repository.findById(id).orElseThrow();
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