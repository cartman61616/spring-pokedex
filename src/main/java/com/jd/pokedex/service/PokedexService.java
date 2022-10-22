package com.jd.pokedex.service;

import com.jd.pokedex.model.Pokemon;
import com.jd.pokedex.repository.PokedexRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Builder
public class PokedexService {

    private final PokedexRepository repo;

    public List<Pokemon> retrieveAllPokemon() {
        return repo.findAll();
    }

    public Pokemon retrievePokemonByName(String name) {
        return repo.findByName(name);
    }

    public void createPokedexEntry(Pokemon pokemon) {
        repo.save(pokemon);
    }

    public Pokemon updatePokedexEntry(Pokemon pokemon) {
        var pokedexEntry = Pokemon.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .build();
        return repo.save(pokedexEntry);
    }

    public void deletePokedexEntry(String name) {
        var pokemon = Optional.ofNullable(repo.findByName(name));
        pokemon.ifPresent(repo::delete);
    }
}
