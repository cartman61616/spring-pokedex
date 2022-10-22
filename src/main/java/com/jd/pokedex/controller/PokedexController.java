package com.jd.pokedex.controller;

import com.jd.pokedex.model.Pokemon;
import com.jd.pokedex.service.PokedexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PokedexController {

    private final PokedexService pokedexService;

    @GetMapping("/pokedex")
    public ResponseEntity<List<Pokemon>> getAllPokemon() {
        List<Pokemon> pokemon;
        pokemon = pokedexService.retrieveAllPokemon();

        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @GetMapping("/pokedex/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable("name") String name) {
        var pokemonData = Optional.ofNullable(pokedexService.retrievePokemonByName(name));

        return pokemonData.map(
                pokemon -> new ResponseEntity<>(pokemon, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/pokedex")
    public ResponseEntity<Pokemon> createPokedexEntry(@RequestBody Pokemon pokemon) {
        try {
            var pokedexEntry = pokedexService.createPokedexEntry(pokemon);
            log.info("New pokedex entry created");
            return new ResponseEntity<>(pokedexEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("error creating new pokemon: {}", pokemon.getName());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
