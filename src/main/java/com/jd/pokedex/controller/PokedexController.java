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
            pokedexService.createPokedexEntry(pokemon);
            log.info("New pokedex entry created");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating new pokemon: {}", pokemon.getName());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pokedex/{name}")
    public ResponseEntity<Pokemon> updatePokedexEntry(@PathVariable("name") String name, @RequestBody Pokemon pokemon) {
        var pokedexEntry = Optional.ofNullable(pokedexService.retrievePokemonByName(name));

        return pokedexEntry.map(
                value -> new ResponseEntity<>(
                        pokedexService.updatePokedexEntry(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/pokedex/{name}")
    public ResponseEntity<HttpStatus> deletePokedexEntry(@PathVariable String name) {
        try {
            pokedexService.deletePokedexEntry(name);
            log.info("Deleting pokemon: {}", name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error deleting pokemon: {}", name);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
