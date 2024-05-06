package Presentation.Screens;

import Presentation.ScreenChangeListener;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartScreen extends JPanel {
    private ScreenChangeListener screenChangeListener;

    public StartScreen(ScreenChangeListener screenChangeListener) {
        this.screenChangeListener = screenChangeListener;
        initializeUI();
    }

    private void initializeUI() {
        JLabel backgroundLabel = addBackgroundImage();
        //BUTTONS -------------------------------------------------------------------------------------
        JButton playButton = new JButton("Jugar");
        JButton logButton = new JButton("Ver Logs");
        logButton.setOpaque(true);
        logButton.setContentAreaFilled(false);
        logButton.setBorderPainted(false);
        logButton.setHorizontalAlignment(SwingConstants.RIGHT);
        JButton closeButton = new JButton("Salir");
        closeButton.setOpaque(true);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setHorizontalAlignment(SwingConstants.RIGHT);

        // Add a temporary mouse listener
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(Color.RED); // Change color on hover to confirm button is responsive
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(Color.BLACK); // Change color back
            }
        });

        backgroundLabel.add(playButton);
        backgroundLabel.add(logButton);
        backgroundLabel.add(closeButton);

        Insets insets = this.getInsets();
        Dimension size = playButton.getPreferredSize();
        playButton.setBounds(500 , 250 + insets.top,
                size.width + 133, size.height + 33);

        size = logButton.getPreferredSize();
        logButton.setBounds(1000 + insets.left, 520 + insets.top,
                size.width + 63, size.height + 4);

        size = closeButton.getPreferredSize();
        closeButton.setBounds(1000 + insets.left, 550 + insets.top,
                size.width + 90, size.height + 4);


        // Add an action listener to the play button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (screenChangeListener != null) {
                    screenChangeListener.onScreenChange(new GreetingScreen(screenChangeListener));
                }
            }
        });
    }

    private JLabel addBackgroundImage() {
        //BACKGROUND IMAGE -------------------------------------------------------------------------------------
        JLabel backgroundLabel = new JLabel(new ImageIcon("src/handdrawn-map.jpg"));
        backgroundLabel.setSize(1200, 650);
        this.add(backgroundLabel);
        return backgroundLabel;
    }

}
