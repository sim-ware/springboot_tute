package com.example.demo.beer;

import org.springframework.data.jpa.repository.JpaRepository;

interface BeerRepository extends JpaRepository<Beer, Long> {
}

// Add a BeerRepository class that leverages Spring Data to do CRUD on this entity.