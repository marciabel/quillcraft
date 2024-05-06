package Presentation.Screens;

import Presentation.ScreenChangeListener;

import javax.swing.*;

public class MainWindowScreen extends JFrame implements ScreenChangeListener {
    private JFrame window;

    public MainWindowScreen() {
        //MAIN WINDOW -------------------------------------------------------------------------------------
        this.window = new JFrame("Quillcraft");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TO DO
        // window.setIconImage(new ImageIcon(imgURL).getImage());
        //window.pack();

        window.setSize(1200, 650);
        window.setResizable(false);

        loadStartingScreen();

        // Make the frame visible
        window.setVisible(true);
    }

    private void loadStartingScreen() {
        StartScreen startingScreen = new StartScreen(this);
        window.setContentPane(startingScreen);
        window.revalidate();
    }

    @Override
    public void onScreenChange(JPanel newScreen) {
        window.setContentPane(newScreen);
        window.revalidate();
        window.repaint();
    }

}
