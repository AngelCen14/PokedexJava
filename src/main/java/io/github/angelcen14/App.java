package io.github.angelcen14;

import io.github.angelcen14.json.PokemonJSON;
import io.github.angelcen14.json.types.TypesJSON;
import io.github.angelcen14.services.PokeApiService;
import io.github.angelcen14.views.AppWindow;

public class App {
    public static void main(String[] args) {
        AppWindow appWindow = new AppWindow();

        PokeApiService pokeApiService = new PokeApiService();

        /*for (String dir : pokeApiService.getPokemonList()) {
            System.out.println(dir);
        }*/

        PokemonJSON pokemonJSON = pokeApiService.getPokemonById(1);

        appWindow.getAppPanel().getPkNameLbl().setText("Nombre: "+pokemonJSON.getName());
        appWindow.getAppPanel().getPkIdLbl().setText("Id: "+ pokemonJSON.getId());
        appWindow.getAppPanel().getPkHeightLbl().setText("Altura: "+ pokemonJSON.getHeight());
        appWindow.getAppPanel().getPkWeightLbl().setText("Peso: "+ pokemonJSON.getWeight());

        String typesText = "Tipos: ";
        for (TypesJSON typesJSON : pokemonJSON.getTypes()) {
            typesText+=typesJSON.getType().getName()+" ";
        }

        appWindow.getAppPanel().getPkTypesLbl().setText(typesText);
    }
}
