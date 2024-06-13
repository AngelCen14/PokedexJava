package io.github.angelcen14.controllers;

import java.util.List;

public class ApiController {
    private final static String API_URL = PropertiesController.getValue("api.url");
    private final static String POKEMON_URL = PropertiesController.getValue("api.pokemon");

    public static List<String> getApiDirList() {
        List<String> dirList;
        String result = HttpController.get(API_URL);
        dirList = List.of(result
                .replace("{", "")
                .replace("}", "")
                .split(","));
        return dirList;
    }
}
