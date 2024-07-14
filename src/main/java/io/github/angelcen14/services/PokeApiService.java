package io.github.angelcen14.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.angelcen14.controllers.HttpController;
import io.github.angelcen14.config.PropertiesManager;
import io.github.angelcen14.exceptions.PokeApiException;
import io.github.angelcen14.exceptions.PokemonNotFoundException;
import io.github.angelcen14.json.PokemonJSON;
import io.github.angelcen14.json.PokemonListJSON;

import java.util.List;

public class PokeApiService {
    private final static String API_URL = PropertiesManager.getValue("api.url");
    private final static String POKEMON_LIST_URL = PropertiesManager.getValue("api.pokemon.list");
    private final static String POKEMON_URL = PropertiesManager.getValue("api.pokemon.id");

    private ObjectMapper objectMapper;

    public PokeApiService() {
        objectMapper = new ObjectMapper();
    }

    public List<String> getApiDirList() {
        List<String> dirList;
        String result = HttpController.get(API_URL);
        dirList = List.of(result
                .replace("{", "")
                .replace("}", "")
                .split(","));
        return dirList;
    }

    public List<String> getPokemonList() {
        List<String> pokemonList;
        String result = HttpController.get(POKEMON_LIST_URL);
        pokemonList = List.of(result.split(","));
        return pokemonList;
    }

    public int getTotalPokemon() throws PokeApiException {
        String responseJson = HttpController.get(POKEMON_LIST_URL);
        objectMapper = new ObjectMapper();
        PokemonListJSON pokemonListJSON = null;
        try {
            pokemonListJSON = objectMapper.readValue(responseJson, PokemonListJSON.class);
            return pokemonListJSON.getCount();
        } catch (JsonProcessingException | NullPointerException e) {
            throw new PokeApiException(e);
        }
    }

    public PokemonJSON getPokemonById(int id) throws PokemonNotFoundException, PokeApiException {
        /*
         * TODO: Por algun motivo en la api despues del id 1025 pasa al 10001 y sigue desde ahi
         * asi que hay que adaptar este codigo
         */
        if (id > 0 && id < /*getTotalPokemon()*/ 1026) {
            String responseJson = HttpController.get(String.format(POKEMON_URL, id));
            objectMapper = new ObjectMapper();
            PokemonJSON pokemonJSON = null;
            try {
                pokemonJSON = objectMapper.readValue(responseJson, PokemonJSON.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return pokemonJSON;
        } else throw new PokemonNotFoundException("Unable to found pokemon with id "+id+" in "+POKEMON_LIST_URL);
    }
}
