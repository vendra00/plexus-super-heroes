package com.plexus.superheroes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plexus.superheroes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
