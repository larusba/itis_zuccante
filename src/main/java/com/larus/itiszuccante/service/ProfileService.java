package com.larus.itiszuccante.service;

import com.larus.itiszuccante.domain.Profile;

public interface ProfileService {

	Profile create(Profile p);
	Profile read(String id);
	Profile update(Profile p);
	void delete(String id);

}