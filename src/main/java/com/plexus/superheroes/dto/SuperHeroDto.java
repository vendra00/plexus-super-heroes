package com.plexus.superheroes.dto;

import com.plexus.superheroes.model.SuperHero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SuperHeroDto {
	
	private String name;
	
	public SuperHeroDto (SuperHero superHero) {
		this.name = superHero.getName();
	}
}
