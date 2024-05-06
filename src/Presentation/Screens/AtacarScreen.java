package Presentation.Screens;

import Presentation.ScreenChangeListener;

import javax.swing.*;

public class AtacarScreen extends JPanel {
    private ScreenChangeListener screenChangeListener;

    public AtacarScreen (ScreenChangeListener screenChangeListener) {
        this.screenChangeListener = screenChangeListener;
        initializeUI();
    }

    private void initializeUI() {

    }
}
