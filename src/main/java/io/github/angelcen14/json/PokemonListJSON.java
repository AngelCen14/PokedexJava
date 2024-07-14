package io.github.angelcen14.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonListJSON {
    private int count;
    private String next;
    private String previous;
}
