package com.larus.itiszuccante.service;


import com.larus.itiszuccante.domain.Badge;

import java.util.Optional;

public interface BadgeService {

	Badge create(Badge b);
	Optional<Badge> read(String id);
	Badge update(Badge b);
	void delete(String id);

}
