package com.example.demo.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PlayerController {
    private PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/top-scorers")
    public Collection<Player> goodBeers() {
        return repository.findAll().stream().collect(Collectors.toList());
    }
}