package com.plexus.superheroes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plexus.superheroes.dto.SuperHeroDto;
import com.plexus.superheroes.model.SuperHero;
import com.plexus.superheroes.repository.SuperHeroRepository;
import com.plexus.superheroes.utils.TrackExecutionTime;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

	@Autowired
	SuperHeroRepository superHeroRepository;

	@TrackExecutionTime
	@Override
	public Optional<SuperHero> getSuperHeroById(Long id) {
		return superHeroRepository.findById(id);
	}

	@TrackExecutionTime
	@Override
	public SuperHero getSuperHeroByName(String name) {
		return superHeroRepository.findByName(name);
	}

	@TrackExecutionTime
	@Override
	public SuperHero insertSuperHero(SuperHero superHero) {
		return superHeroRepository.save(superHero);
	}

	@TrackExecutionTime
	@Override
	public void updateSuperHero(Long id, SuperHeroDto superHero) {
		SuperHero objectFromDb = superHeroRepository.findById(id).get();
		objectFromDb.setName(superHero.getName());
		superHeroRepository.save(objectFromDb);
	}

	@TrackExecutionTime
	@Override
	public void deleteSuperHero(Long id) {
		superHeroRepository.deleteById(id);
	}

	@TrackExecutionTime
	@Override
	public List<SuperHeroDto> findAllSuperHeroes() {
		return ((List<SuperHero>) superHeroRepository.findAll()).stream().map(this::convertToSuperHeroDto).collect(Collectors.toList());
	}

	@TrackExecutionTime
	@Override
	public List<SuperHeroDto> getSuperHeroesByName(String name) {
		List<SuperHeroDto> listOfHeroes = new ArrayList<SuperHeroDto>();
		for (SuperHeroDto superHero : findAllSuperHeroes()) {
			if(superHero.getName().toLowerCase().contains(name.toLowerCase())) {
				listOfHeroes.add(superHero);
			}
		}
		return listOfHeroes;
	}

	private SuperHeroDto convertToSuperHeroDto(SuperHero entity) {
		SuperHeroDto dto = new SuperHeroDto();
		dto.setName(entity.getName());
		return dto;
	}

}
