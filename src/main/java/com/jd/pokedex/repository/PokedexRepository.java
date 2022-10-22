package com.jd.pokedex.repository;

import com.jd.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokedexRepository extends JpaRepository<Pokemon, Integer> {
    Pokemon findByName(String name);
}
