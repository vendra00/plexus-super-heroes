package com.plexus.superheroes.service;

import java.util.List;
import java.util.Optional;

import com.plexus.superheroes.dto.SuperHeroDto;
import com.plexus.superheroes.model.SuperHero;

public interface SuperHeroService {

	Optional<SuperHero> getSuperHeroById(Long id);
	
	SuperHero getSuperHeroByName(String name);
	
	List<SuperHeroDto> getSuperHeroesByName(String name);
	
	List<SuperHeroDto> findAllSuperHeroes();
	
	SuperHero insertSuperHero(SuperHero superHero);
	
	void updateSuperHero(Long id, SuperHeroDto superHero);

    void deleteSuperHero(Long id);

}
