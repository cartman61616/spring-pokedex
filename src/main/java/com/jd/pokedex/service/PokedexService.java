package com.jd.pokedex.service;

import com.jd.pokedex.model.Pokemon;
import com.jd.pokedex.repository.PokedexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PokedexService {

    private final PokedexRepository repo;

    public List<Pokemon> retrieveAllPokemon() {
        return repo.findAll();
    }

    public Pokemon retrievePokemonByName(String name) {
        return retrievePokemonByName(name);
    }

    public Pokemon createPokedexEntry(Pokemon pokemon) {
        return repo.save(pokemon);
    }
}
