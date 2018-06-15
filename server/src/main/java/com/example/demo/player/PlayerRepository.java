package com.example.demo.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface PlayerRepository extends JpaRepository<Player, Long> {
}

// Add a BeerRepository class that leverages Spring Data to do CRUD on this entity.