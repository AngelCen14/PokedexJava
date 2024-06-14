package io.github.angelcen14.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.angelcen14.json.types.TypesJSON;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonJSON {
    private String name;
    private int id;
    private float height;
    private float weight;
    private List<TypesJSON> types;
}
