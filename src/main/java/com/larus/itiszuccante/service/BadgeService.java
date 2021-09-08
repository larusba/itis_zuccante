package com.larus.itiszuccante.service;


import com.larus.itiszuccante.domain.Badge;

public interface BadgeService {

	Badge create(Badge b);
	Badge read(String id);
	Badge update(Badge b);
	void delete(String id);

}
