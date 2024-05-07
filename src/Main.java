import Presentation.Console.Menu;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            Menu.startMenu();
        } catch (ParseException e) {
            System.out.println("Hubo un error al iniciar la partida");
        }
    }
}