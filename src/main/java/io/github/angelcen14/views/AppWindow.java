package io.github.angelcen14.views;

import lombok.Getter;

import javax.swing.*;

@Getter
public class AppWindow extends JFrame {

    private AppPanel appPanel;

    public AppWindow () {
        appPanel = new AppPanel();

        setTitle("Pokedex");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(appPanel);
        setVisible(true);
    }
}
