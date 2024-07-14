package io.github.angelcen14.controllers;

import io.github.angelcen14.exceptions.PokeApiException;
import io.github.angelcen14.exceptions.PokemonNotFoundException;
import io.github.angelcen14.json.PokemonJSON;
import io.github.angelcen14.services.PokeApiService;

public class PokedexController {

    private static int currentPokemonId = 1;

    private static PokeApiService pokeApiService = new PokeApiService();

    public static PokemonJSON getCurrentPokemon() throws PokeApiException {
        PokemonJSON pokemonJSON = null;
        try {
            pokemonJSON = pokeApiService.getPokemonById(currentPokemonId);
        } catch (PokemonNotFoundException e) {
            e.printStackTrace();
        }
        return pokemonJSON;
    }

    public static PokemonJSON getNextPokemon() throws PokeApiException {
        currentPokemonId++;
        PokemonJSON pokemonJSON = null;
        try {
            pokemonJSON = pokeApiService.getPokemonById(currentPokemonId);
        } catch (PokemonNotFoundException e) {
            currentPokemonId--;
        }
        return pokemonJSON;
    }

    public static PokemonJSON getPreviousPokemon() throws PokeApiException {
        currentPokemonId--;
        PokemonJSON pokemonJSON = null;
        try {
            pokemonJSON = pokeApiService.getPokemonById(currentPokemonId);
        } catch (PokemonNotFoundException e) {
            currentPokemonId++;
        }
        return pokemonJSON;
    }
}
