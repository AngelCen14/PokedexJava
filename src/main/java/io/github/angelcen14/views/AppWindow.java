package io.github.angelcen14.views;

import io.github.angelcen14.controllers.PokedexController;
import io.github.angelcen14.exceptions.PokeApiException;
import io.github.angelcen14.json.PokemonJSON;
import io.github.angelcen14.json.types.TypesJSON;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppWindow extends JFrame {
    // Componentes de la interfaz
    private JPanel appPanel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel heightLabel;
    private JLabel weightLabel;
    private JLabel typesLabel;
    private JButton buttonPrevious;
    private JButton buttonNext;

    public AppWindow() {
        setWindowSettings();
        setListeners();
        onWindowLoad();
        setVisible(true);
    }

    private void setWindowSettings() {
        setTitle("Pokedex");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(appPanel);
    }

    private void setListeners() {
        buttonPrevious.addActionListener(buttonPreviousActionListener);
        buttonNext.addActionListener(buttonNextActionListener);
    }

    private void onWindowLoad() {
        try {
            tryUpdatePokemonDisplay(PokedexController.getCurrentPokemon());
        } catch (PokeApiException e) {
            handlePokeApiException();
        }
    }

    private boolean tryUpdatePokemonDisplay(PokemonJSON pokemonJSON) {
        if (pokemonJSON != null) {
            nameLabel.setText("Nombre: "+pokemonJSON.getName());
            idLabel.setText("Id: "+ pokemonJSON.getId());
            heightLabel.setText("Altura: "+ pokemonJSON.getHeight());
            weightLabel.setText("Peso: "+ pokemonJSON.getWeight());

            String typesText = "Tipos: ";
            for (TypesJSON typesJSON : pokemonJSON.getTypes()) {
                typesText+=typesJSON.getType().getName()+" ";
            }
            typesLabel.setText(typesText);
            return true;
        }
        return false;
    }

    // --- LISTENERS ---
    private final ActionListener buttonPreviousActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!tryUpdatePokemonDisplay(PokedexController.getPreviousPokemon())) {
                    JOptionPane.showMessageDialog(null, "El pokemon solicitado no existe");
                }
            } catch (PokeApiException ex) {
                handlePokeApiException();
            }
        }
    };

    private final ActionListener buttonNextActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!tryUpdatePokemonDisplay(PokedexController.getNextPokemon())) {
                    JOptionPane.showMessageDialog(null, "El pokemon solicitado no existe");
                }
            } catch (PokeApiException ex) {
                handlePokeApiException();
            }
        }
    };

    private void handlePokeApiException() {
        JOptionPane.showMessageDialog(
                this,
                "Se ha producido un error al conectar con PokeApi",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        System.exit(1);
    }
}
