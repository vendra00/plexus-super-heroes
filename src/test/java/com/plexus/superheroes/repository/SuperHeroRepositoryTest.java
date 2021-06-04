package com.plexus.superheroes.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plexus.superheroes.model.SuperHero;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SuperHeroRepositoryTest {
	
	@Autowired
	private SuperHeroRepository repository;

	@Test
	public void shouldLoadASuperHeroAfterSearchByName() {
		String name = "Ant Man";
		SuperHero superHero = repository.findByName(name);
		
		Assert.assertNotNull(superHero);
		Assert.assertEquals(name, superHero.getName());
	}
	
	@Test
	public void shouldNotLoadASuperHeroAfterSearchByName() {
		String name = "Homelander";
		SuperHero superHero = repository.findByName(name);
		
		Assert.assertNull(superHero);
	}
}
