package Presentation.Console;

import Business.NumberGenerator;
import Model.Personaje;

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

    public static void imprimirInicioConfrontacion() {
        List<String> frasesInicioConfrontacion = List.of(
                "La arena retumba mientras dos héroes legendarios cruzan miradas, listos para el choque de titanes.",
                "Como dos estrellas en colisión, los campeones se encuentran en una danza mortal de acero y magia.",
                "El destino converge en este momento, donde dos guerreros enfrentan sus almas en un combate épico.",
                "El aire se carga con la promesa de un combate inolvidable mientras dos leyendas se preparan para luchar.",
                "En el crepúsculo del campo de batalla, dos figuras imponentes se encaran, con la victoria en la mira.",
                "La tensión se palpa cuando dos héroes, cada uno con su propio legado, se desafían en un duelo sin igual.",
                "Dos almas valientes se encuentran en la encrucijada del destino, sus armas listas para narrar su historia.",
                "Las leyendas cobran vida mientras dos personajes imparables chocan en una lucha por la gloria eterna.",
                "Con un rugido que resuena a través de los eras, dos poderosos adversarios se lanzan al combate decisivo.",
                "El choque de voluntades se hace físico mientras dos campeones se enfrentan, con sus ojos puestos en la victoria.",
                "El susurro del viento cuenta la llegada de un duelo sin precedentes entre dos maestros de la guerra.",
                "La tierra tiembla y el cielo oscurece cuando dos figuras épicas se preparan para su batalla definitiva.",
                "Un silencio sepulcral precede al estruendo del enfrentamiento entre dos titanes de leyenda.",
                "Dos héroes, dos destinos entrelazados, se encuentran en la cima del mundo para un duelo de honor.",
                "La historia se escribe con cada golpe y conjuro en este choque de almas guerreras.",
                "Como el choque de olas contra la roca, dos fuerzas de la naturaleza se encuentran en un enfrentamiento épico.",
                "La chispa de la competición enciende un fuego feroz en el corazón de dos competidores inmortales.",
                "El destino ha hilado su red, atrapando a dos combatientes en una danza de destreza y poder.",
                "Un duelo de miradas precede al choque de espadas, donde solo uno proclamará la victoria.",
                "En un mundo forjado en la eternidad, dos aventureros se baten en duelo bajo la mirada de los dioses."
        );

        System.out.println(frasesInicioConfrontacion.get(NumberGenerator.generateRandomPositiveInteger(1,20)-1));
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
        System.out.println("            | EL JUGADOR VICTORIOSO ES " + jugadorGanador.toUpperCase() + " |");
        System.out.println("˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚");
    }

    public static void menuLogs() {
        System.out.println(" ----------------------------------------------------------------------- ");
        System.out.println(
                "               Que desea hacer?\n" +
                        "\n" +
                        "[1] Borrar logs\n" +
                        "[2] Volver al menu principal\n" +
                        "Seleccione una opcion ingresando el numero correspondiente:\n");
    }
}
