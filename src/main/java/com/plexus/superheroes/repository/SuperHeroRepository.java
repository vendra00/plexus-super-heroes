package com.plexus.superheroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plexus.superheroes.model.SuperHero;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long>{
	SuperHero findByName(String name);
}
