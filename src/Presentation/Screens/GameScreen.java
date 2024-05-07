package Presentation.Screens;

import Business.PartidaService;
import Model.Jugador;
import Model.Personaje;
import Presentation.ScreenChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScreen extends JPanel {
    private ScreenChangeListener screenChangeListener;

    public GameScreen(ScreenChangeListener screenChangeListener, String nombreJugador) {
        this.screenChangeListener = screenChangeListener;
        PartidaService nuevaPartida = new PartidaService(nombreJugador);
        //initializeUI(nuevaPartida.inicializarPersonajes());
    }

    private void initializeUI(List<Jugador> jugadores) {
        JLabel backgroundLabel = addBackgroundImage();

        // Cargar cartas --------------------------------------------------------------------------
        Map<Jugador, List<JLabel>> cartasPorJugador = new HashMap<>();

        for (Jugador jugador :jugadores) {
            if (!cartasPorJugador.containsKey(jugador)) {
                cartasPorJugador.put(jugador, new ArrayList<>());
            }
            for (Personaje personaje :jugador.getPersonajes()) {
                JLabel cardImage =
                        addCard(
                                personaje.getNombre(),
                                personaje.getNivel(),
                                personaje.getImagen(),
                                personaje.getSalud(),
                                personaje.getArmadura()
                        );
                cartasPorJugador.get(jugador).add(cardImage);
            }
        }

        int[][] posicionesCartasJugador = {
                {35, 310},    // Posición inicial para la primera carta del primer jugador
                {224, 310},   // Posición para la segunda carta del primer jugador
                {413, 310},   // Posición para la tercera carta del primer jugador
        };

        int[][] posicionesCartasMaquina = {
                {548, 15},  // Posición inicial para la primera carta del segundo jugador
                {737, 15},  // Posición para la segunda carta del segundo jugador
                {926, 15}   // Posición para la tercera carta del segundo jugador
        };

        Insets insets = this.getInsets();
        int indiceJugador1 = 0;
        int indiceJugador2 = 0;

        for (Map.Entry<Jugador, List<JLabel>> entrada : cartasPorJugador.entrySet()) {
            Jugador jugador = entrada.getKey(); // El jugador actual
            List<JLabel> cartas = entrada.getValue(); // La lista de cartas de ese jugador

            System.out.println("Cartas para el jugador: " + jugador.getNombre()); // Asumiendo que Jugador tiene un método getNombre()

            // Determina qué conjunto de posiciones usar y cuál índice incrementar
            int[][] posicionesCartas = jugadores.indexOf(jugador) == 0 ? posicionesCartasJugador : posicionesCartasMaquina;
            int indice = jugadores.indexOf(jugador) == 0 ? indiceJugador1 : indiceJugador2;

            // Iterar sobre la lista de JLabels (cartas) para ese jugador
            for (JLabel carta : cartas) {
                backgroundLabel.add(carta);
                Dimension size = carta.getPreferredSize();

                // Configura las coordenadas de la carta
                carta.setBounds(
                        posicionesCartas[indice][0] + insets.left,
                        posicionesCartas[indice][1] + insets.top,
                        size.width + 20,
                        size.height + 4
                );

                // Incrementa el índice apropiado dependiendo del jugador
                if (jugadores.indexOf(jugador) == 0) {
                    indiceJugador1++;
                } else {
                    indiceJugador2++;
                }

                // Actualiza el índice para el siguiente uso en el bucle
                indice = jugadores.indexOf(jugador) == 0 ? indiceJugador1 : indiceJugador2;
            }
        }

        // Boton --------------------------------------------------------------------------
        JButton startButton = new JButton("Comenzar");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screenChangeListener != null) {
                    screenChangeListener.onScreenChange(new AtacarScreen(screenChangeListener));
                }
            }
        });

        backgroundLabel.add(startButton);

        Dimension size = startButton.getPreferredSize();
        startButton.setBounds(950 , 530 + insets.top,
                size.width + 50, size.height+20);

    }

    private JLabel addBackgroundImage() {
        //BACKGROUND IMAGE -------------------------------------------------------------------------------------
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/handdrawn-map.jpg"));
        backgroundLabel.setSize(200, 400);
        this.add(backgroundLabel);
        return backgroundLabel;
    }

    private JLabel addCard(String nombre, Integer nivel, String descripcion, Integer salud, Integer armadura) {
        Integer desiredWidth = 179;
        Integer desiredHeight = 280;

        // Create a new ImageIcon from the resized image
        ImageIcon resizedIcon = new ImageIcon(reziseImage("src/cartaBlanca.png", desiredWidth, desiredHeight));

        // Create the JLabel with the resized ImageIcon
        JLabel cardImage = new JLabel(resizedIcon);
        cardImage.setSize(desiredWidth, desiredHeight);

        populateCard(cardImage, nombre, nivel, descripcion, salud, armadura);

        return cardImage;
    }

    private void populateCard(
            JLabel cardImage,
            String nombrePersonaje,
            Integer nivelPersonaje,
            String descripcionPersonaje,
            Integer saludPersonaje,
            Integer armaduraPersonaje
    ) {
        JLabel nombre = new JLabel(nombrePersonaje);
        JLabel nivel = new JLabel(nivelPersonaje+"");
        JLabel descripcion = new JLabel(descripcionPersonaje);
        JLabel salud = new JLabel(saludPersonaje+"");
        JLabel armadura = new JLabel(armaduraPersonaje+"");

        Insets insets = this.getInsets();
        Dimension size = nombre.getPreferredSize();
        nombre.setBounds(35 , 20 + insets.top,
                size.width + 20, size.height);
        size = nivel.getPreferredSize();
        nivel.setBounds(155 , 20 + insets.top,
                size.width + 20, size.height);
        size = descripcion.getPreferredSize();
        descripcion.setBounds(35 , 170 + insets.top,
                size.width, size.height);
        size = salud.getPreferredSize();
        salud.setBounds(30 , 245 + insets.top,
                size.width + 20, size.height);
        size = armadura.getPreferredSize();
        armadura.setBounds(155 , 245 + insets.top,
                size.width + 20, size.height);

        cardImage.add(nombre);
        cardImage.add(nivel);
        cardImage.add(descripcion);
        cardImage.add(salud);
        cardImage.add(armadura);
    }

    private Image reziseImage(String imagePath, Integer desiredWidth, Integer desiredHeight) {
        // Load the original ImageIcon
        ImageIcon originalIcon = new ImageIcon(imagePath);

        // Get the original image
        Image originalImage = originalIcon.getImage();

        // Calculate the new size while maintaining the aspect ratio
        double aspectRatio = (double) originalIcon.getIconWidth() / (double) originalIcon.getIconHeight();

        Integer newWidth;
        Integer newHeight;

        // Determine how to scale the image
        if (originalIcon.getIconWidth() > originalIcon.getIconHeight()) {
            // If the image is wider than it is tall
            newWidth = desiredWidth;
            newHeight = (int) (desiredWidth / aspectRatio);
            if (newHeight > desiredHeight) {
                // Adjust height if it's greater than desired
                newHeight = desiredHeight;
                newWidth = (int) (desiredHeight * aspectRatio);
            }
        } else {
            // If the image is taller than it is wide
            newHeight = desiredHeight;
            newWidth = (int) (desiredHeight * aspectRatio);
            if (newWidth > desiredWidth) {
                // Adjust width if it's greater than desired
                newWidth = desiredWidth;
                newHeight = (int) (desiredWidth / aspectRatio);
            }
        }

        // Resize the image
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return resizedImage;
    }

}
