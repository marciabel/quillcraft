package Presentation.Screens;

import Presentation.ScreenChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingScreen extends JPanel{
    private ScreenChangeListener screenChangeListener;
    private String playerName;

    public GreetingScreen(ScreenChangeListener screenChangeListener) {
        this.screenChangeListener = screenChangeListener;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Ingresa tu nombre:");
        JTextField nameField = new JTextField(20);
        JButton submitButton = new JButton("Ir");
        JButton goBackButton = new JButton("Volver");

        // Listener para el campo de texto que guarda el nombre del jugador
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText(); // Guardar el nombre del jugador
                proceedToGame(); // Proceder al juego
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screenChangeListener != null) {
                    screenChangeListener.onScreenChange(new StartScreen(screenChangeListener));
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText(); // Guardar el nombre del jugador
                proceedToGame(); // Proceder al juego
            }
        });

        // Agregar componentes al panel
        this.add(nameLabel);
        this.add(nameField);
        this.add(submitButton);
        this.add(goBackButton);

    }

    private void proceedToGame() {
        if (screenChangeListener != null && !playerName.isEmpty()) {
            screenChangeListener.onScreenChange(new GameScreen(screenChangeListener, playerName));
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa tu nombre.", "Nombre requerido", JOptionPane.WARNING_MESSAGE);
        }
    }
}
