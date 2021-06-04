package com.plexus.superheroes.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.plexus.superheroes.dto.SuperHeroDto;
import com.plexus.superheroes.exception.SuperHeroException;
import com.plexus.superheroes.model.SuperHero;
import com.plexus.superheroes.service.SuperHeroService;
import com.plexus.superheroes.utils.TrackExecutionTime;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/super-heroes")
public class SuperHeroController {

	@Autowired
	private SuperHeroService superHeroService;

	@TrackExecutionTime
	@Cacheable(value = "listAllSuperHeroes")
	@GetMapping("/find-all-superheroes")
	public ResponseEntity<Collection<SuperHeroDto>> getAllSuperHeroes() throws SuperHeroException {
		try {
			return ResponseEntity.ok(superHeroService.findAllSuperHeroes());
		} catch (Exception e) {
			throw new SuperHeroException("There was a problem at loading the Super Heroes list");
		}
	}

	@TrackExecutionTime
	@Cacheable(value = "listAllSuperHeroes")
	@GetMapping("/find-all-superheroes-match-name/{name}")
	public ResponseEntity<Collection<SuperHeroDto>> getAllSuperHeroesMatchName(@PathVariable("name") String name) throws SuperHeroException {
		try {
			return ResponseEntity.ok(superHeroService.getSuperHeroesByName(name));
		} catch (Exception e) {
			throw new SuperHeroException("There was a problem at loading the Super Heroes list");
		}
	}

	@TrackExecutionTime
	@Cacheable(value = "listAllSuperHeroes")
	@GetMapping(value = "/find-superhero-by-id/{id}")
	ResponseEntity<SuperHeroDto> getSuperHeroById(@PathVariable("id") Long id) {
		SuperHero superHero = superHeroService.getSuperHeroById(id).orElseThrow(() -> new SuperHeroException("No Super Hero with ID : " + id));
		return ResponseEntity.ok().body(new SuperHeroDto(superHero));
	}

	@TrackExecutionTime
	@CacheEvict(value = "listAllSuperHeroes", allEntries = true)
	@PostMapping({ "/insert-superhero/" })
	public ResponseEntity<SuperHeroDto> addSuperHero(@RequestBody SuperHero newSuperHero) throws SuperHeroException {
		try {
			SuperHero superHeroBody = superHeroService.insertSuperHero(newSuperHero);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(superHeroBody.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			throw new SuperHeroException(
					"There is a Super Heroes in the DB with this name : " + newSuperHero.getName());
		}

	}

	@TrackExecutionTime
	@CacheEvict(value = "listAllSuperHeroes", allEntries = true)
	@PutMapping(value = "/update-superhero/{id}")
	ResponseEntity<SuperHeroDto> updateSuperHero(@PathVariable("id") Long id, @RequestBody SuperHeroDto superHero) throws SuperHeroException {
		try {
			superHeroService.updateSuperHero(id, superHero);
			return ResponseEntity.ok().body(superHero);
		} catch (Exception e) {
			throw new SuperHeroException("No Super Hero with ID : " + id);
		}

	}

	@TrackExecutionTime
	@CacheEvict(value = "listAllSuperHeroes", allEntries = true)
	@DeleteMapping(value = "/delete-superhero/{id}")
	ResponseEntity<String> deleteSuperHero(@PathVariable("id") Long id) {
		SuperHero superHero = superHeroService.getSuperHeroById(id)
				.orElseThrow(() -> new SuperHeroException("No Super Hero with ID : " + id));
		superHeroService.deleteSuperHero(superHero.getId());
		return ResponseEntity.ok().body("Super Hero with ID : " + id + " deleted with success!");
	}

}
