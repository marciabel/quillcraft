package Presentation.Console;

import Model.Personaje;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrintHelper {
    public static void imprimirPresentacion(){
        System.out.println(
                "|================================================|\n" +
                        "|************************************************|\n" +
                        " _____       _ _ _                 __ _   \n" +
                        "|  _  |     (_) | |               / _| |  \n" +
                        "| | | |_   _ _| | | ___ _ __ __ _| |_| |_ \n" +
                        "| | | | | | | | | |/ __| '__/ _` |  _| __|\n" +
                        "\\ \\/' / |_| | | | | (__| | | (_| | | | |_ \n" +
                        " \\_/\\_\\\\__,_|_|_|_|\\___|_|  \\__,_|_|  \\__|\n" +
                        "|************************************************|\n" +
                        "|================================================|\n" +
                        "\n" +
                        "               INGRESE SU NOMBRE: \n"
        );
    }

    public static void imprimirMenu(){
        System.out.println(
                "               MENU DEL JUEGO\n" +
                "\n" +
                "[1] Empezar una partida aleatoria\n" +
                "[2] Empezar una partida custom\n" +
                "[3] Ver logs\n" +
                "[4] Salir\n" +
                "\n" +
                "Seleccione una opcion ingresando el numero correspondiente:\n");
    }

    public static void imprimirCarta(Personaje personaje, String raza) {
        System.out.println(" /~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\ ");
        System.out.println("(|            "+ raza +"             |)");
        System.out.println(" |----------------------------------| ");
        System.out.println(" | Nombre: " + String.format("%-24s", personaje.getNombre() + (personaje.getApodo() != null ? " (" + personaje.getApodo() + ")" : "")) + " |");
        System.out.println(" | Fecha de Nac.: " + String.format("%-20s", personaje.getFechaNacimiento()) + " |");
        System.out.println(" | Edad: " + String.format("%-28s", personaje.getEdad()) + " |");
        System.out.println(" | Salud: " + String.format("%-27s", personaje.getSalud()) + " |");
        System.out.println(" | Imagen: " + String.format("%-26s", personaje.getImagen()) + " |");
        System.out.println(" | Velocidad: " + String.format("%-23s", personaje.getVelocidad()) + " |");
        System.out.println(" | Destreza: " + String.format("%-24s", personaje.getDestreza()) + " |");
        System.out.println(" | Fuerza: " + String.format("%-26s", personaje.getFuerza()) + " |");
        System.out.println(" | Nivel: " + String.format("%-27s", personaje.getNivel()) + " |");
        System.out.println(" | Armadura: " + String.format("%-24s", personaje.getArmadura()) + " |");
        System.out.println(" \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/ ");
    }

    public static void imprimirCartas(List<Personaje> personajes) {

// Crear una lista para contener las líneas de cada carta
        List<String[]> cartas = new ArrayList<>();

        for (Personaje personaje : personajes) {
            String[] carta = new String[11];
            carta[0] = " /~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\\ ";
            carta[1] = String.format("|\t%-29s|", "PERSONAJE");
            carta[2] = " |-------------------------------| ";
            carta[3] = String.format("|\tNombre: %-21s|", personaje.getNombre());
            carta[4] = String.format("|\tApodo: %-22s|", personaje.getApodo());
            carta[5] = String.format("|\tEdad: %-24s|", personaje.getEdad() + " años");
            carta[6] = String.format("|\tSalud: %-23s|", personaje.getSalud());
            carta[7] = String.format("|\tNivel: %-23s|", personaje.getNivel());
            carta[8] = String.format("|\tArmadura: %-20s|", personaje.getArmadura());
            carta[9] = String.format("|\tFuerza: %-22s|", personaje.getFuerza());
            carta[10] = " \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/ ";
            cartas.add(carta);
        }

// Imprimir las cartas lado a lado
        for (int i = 0; i < 11; i++) { // Asumiendo que cada carta tiene 11 líneas
            for (String[] carta : cartas) {
                System.out.print(carta[i]);
                System.out.print("\t"); // Tabulación entre cartas
            }
            System.out.println(); // Nueva línea después de imprimir cada fila de cartas
        }
    }

    public static void imprimirInicioPartida() {
        System.out.println("====================================================================================================");
        System.out.println("" +
                "  ___ _  _ ___ ___ ___ ___    ___  ___   _      _     ___  _   ___ _____ ___ ___   _   \n" +
                " |_ _| \\| |_ _/ __|_ _/ _ \\  |   \\| __| | |    /_\\   | _ \\/_\\ | _ \\_   _|_ _|   \\ /_\\  \n" +
                "  | || .` || | (__ | | (_) | | |) | _|  | |__ / _ \\  |  _/ _ \\|   / | |  | || |) / _ \\ \n" +
                " |___|_|\\_|___\\___|___\\___/  |___/|___| |____/_/ \\_\\ |_|/_/ \\_\\_|_\\ |_| |___|___/_/ \\_\\\n"
        );
        System.out.println("====================================================================================================");
    }

    public static void imprimirRonda(Integer numeroRonda) {
        System.out.println("\n\n");
        System.out.println("\t\t ,----------------------------------------------------.");
        System.out.println("\t\t/                                                      \\");
        System.out.println("\t\t|                     RONDA " + String.format("%-2d", numeroRonda) + "                        |");
        System.out.println("\t\t\\                                                      /");
        System.out.println("\t\t '----------------------------------------------------'");
        System.out.println("Se enfrentaran los siguientes personajes: ");
    }

    public static void imprimirFinDeLaPartida(String jugadorGanador) {
        System.out.println(
                "______ _____ _   _  ______ _____   _      ___   ______  ___ ______ _____ ___________  ___  \n" +
                        "|  ___|_   _| \\ | | |  _  \\  ___| | |    / _ \\  | ___ \\/ _ \\| ___ \\_   _|_   _|  _  \\/ _ \\ \n" +
                        "| |_    | | |  \\| | | | | | |__   | |   / /_\\ \\ | |_/ / /_\\ \\ |_/ / | |   | | | | | / /_\\ \\\n" +
                        "|  _|   | | | . ` | | | | |  __|  | |   |  _  | |  __/|  _  |    /  | |   | | | | | |  _  |\n" +
                        "| |    _| |_| |\\  | | |/ /| |___  | |___| | | | | |   | | | | |\\ \\  | |  _| |_| |/ /| | | |\n" +
                        "\\_|    \\___/\\_| \\_/ |___/ \\____/  \\_____|_| |_/ \\_|   \\_| |_|_| \\_| \\_/  \\___/|___/ \\_| |_/\n"
        );
        System.out.println("˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚");
        System.out.println("|             EL JUGADOR VICTORIOSO ES " + jugadorGanador.toUpperCase() + "     |");
        System.out.println("˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚");
    }
}
