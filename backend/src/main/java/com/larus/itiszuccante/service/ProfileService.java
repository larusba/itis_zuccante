package com.larus.itiszuccante.service;

import java.util.Optional;

import com.larus.itiszuccante.domain.Profile;

public interface ProfileService {

	Profile create(Profile p);
	Optional<Profile> read(String id);
	Profile update(Profile p);
	void delete(String id);

}