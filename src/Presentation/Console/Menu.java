package Presentation.Console;

import Business.InputValidator;
import Business.LoggerService;
import Business.PartidaService;

import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static PartidaService partida;

    public static void startMenu() throws ParseException {
        PrintHelper.imprimirPresentacion();
        String nombreJugador = sc.next();
        partida = new PartidaService(nombreJugador);

        String opcionMenu;

        do {
            PrintHelper.imprimirMenu();
            opcionMenu = String.valueOf(InputValidator.inputOpcionMenu());

            switch (opcionMenu) {
                case "1":
                    partida.empezarPartidaAleatoria();
                    break;
                case "2":
                    partida.empezarPartidaManual();
                    break;
                case "3":
                    manejarLogs();
                    break;
                case "4":
                    System.out.println("Hasta la proxima!");
                    return;
                default:
                    System.out.println("La opcion ingresada no es correcta");
                    break;
            }
        } while (!opcionMenu.equals("4"));
    }

    private static void manejarLogs() {
        System.out.println("Historial de logs: ");
        LoggerService.getLogFile("game.log");
        PrintHelper.menuLogs();
        String opcionMenu = String.valueOf(InputValidator.inputIntegerRango(1,2));

        switch (opcionMenu) {
            case "1":
                LoggerService.deleteLogs();
                System.out.println("Logs borrados con exito");
                System.out.println("Presione cualquier tecla para volver al menu principal");
                sc.nextLine();sc.nextLine();
                break;
            case "2":
                return;
            default:
                break;
        }
    }
}
