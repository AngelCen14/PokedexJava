package io.github.angelcen14.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.angelcen14.controllers.HttpController;
import io.github.angelcen14.config.PropertiesManager;
import io.github.angelcen14.json.PokemonJSON;

import java.util.List;

public class PokeApiService {
    private final static String API_URL = PropertiesManager.getValue("api.url");
    private final static String POKEMON_LIST_URL = PropertiesManager.getValue("api.pokemon.list");
    private final static String POKEMON_URL = PropertiesManager.getValue("api.pokemon.id");

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

    public PokemonJSON getPokemonById(int id) {
        String responseJson = HttpController.get(String.format(POKEMON_URL, id));
        ObjectMapper objectMapper = new ObjectMapper();
        PokemonJSON pokemonJSON = null;
        try {
             pokemonJSON = objectMapper.readValue(responseJson, PokemonJSON.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pokemonJSON;
    }
}
