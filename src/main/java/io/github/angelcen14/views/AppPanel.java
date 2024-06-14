package io.github.angelcen14.views;

import io.github.angelcen14.json.PokemonJSON;
import io.github.angelcen14.json.types.TypesJSON;
import io.github.angelcen14.services.PokeApiService;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class AppPanel extends JPanel {
    private JLabel pkIdLbl;
    private JLabel pkNameLbl;
    private JLabel pkHeightLbl;
    private JLabel pkWeightLbl;
    private JLabel pkTypesLbl;
    private JButton nextBtn;

    private int pokedexIndex = 1;
    PokeApiService pokeApiService = new PokeApiService();

    public AppPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        pkIdLbl = new JLabel();
        pkNameLbl = new JLabel();
        pkHeightLbl = new JLabel();
        pkWeightLbl = new JLabel();
        pkTypesLbl = new JLabel();

        nextBtn = new JButton();
        nextBtn.setText("Siguiente pokemon");

        // Alinear horizontalmente los JLabels al centro
        pkIdLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pkNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pkHeightLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pkWeightLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pkTypesLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(pkIdLbl);
        add(pkNameLbl);
        add(pkHeightLbl);
        add(pkWeightLbl);
        add(pkTypesLbl);
        add(nextBtn);

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokedexIndex++;
                PokemonJSON pokemonJSON = pokeApiService.getPokemonById(pokedexIndex);

                getPkNameLbl().setText("Nombre: "+pokemonJSON.getName());
                getPkIdLbl().setText("Id: "+ pokemonJSON.getId());
                getPkHeightLbl().setText("Altura: "+ pokemonJSON.getHeight());
                getPkWeightLbl().setText("Peso: "+ pokemonJSON.getWeight());

                String typesText = "Tipos: ";
                for (TypesJSON typesJSON : pokemonJSON.getTypes()) {
                    typesText+=typesJSON.getType().getName()+" ";
                }

                getPkTypesLbl().setText(typesText);
            }
        });
    }
}
