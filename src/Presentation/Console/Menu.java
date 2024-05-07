package Presentation.Console;

import Business.InputValidator;
import Business.PartidaService;
import Model.Elfo;
import Model.Humano;
import Model.Orco;
import Model.Personaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static PartidaService partida;

    public static void startMenu() {
        PrintHelper.imprimirPresentacion();
        String nombreJugador = sc.next();

        PrintHelper.imprimirMenu();
        String opcionMenu = String.valueOf(InputValidator.inputOpcionMenu());

        partida = new PartidaService(nombreJugador);

        switch (opcionMenu) {
            case "1":
                partida.empezarPartidaAleatoria();
                break;
            case "2":
                empezarPartidaManual();
                break;
            case "3":

                break;
            case "4":

                break;
            default:
                System.out.println("La opcion ingresada no es correcta");
                break;
        }
    }

    private static void empezarPartidaManual() {
        List<Personaje> personajesJugador = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("Creacion del proximo personaje: ");
            Personaje personaje = null;

            System.out.println("          PARTIDA DE JUEGO MANUAL\n");

            System.out.println("Ingrese la raza del personaje (HUMANO/ORCO/ELFO): ");
            String raza = InputValidator.inputRaza().toUpperCase();

            switch (raza) {
                case "HUMANO":
                    personaje = new Humano();
                    break;
                case "ORCO":
                    personaje = new Orco();
                    break;
                case "ELFO":
                    personaje = new Elfo();
                    break;
                default:
                    break;
            }

            System.out.println("Ingrese nombre del personaje: ");
            String nombre = InputValidator.inputNombre();
            personaje.setNombre(nombre);

            System.out.println("Ingrese la edad del personaje (Entre 0 y 300): ");
            Integer edad = InputValidator.inputEdad();
            personaje.setEdad(edad);

            System.out.println("Ingrese un apodo para el personaje: ");
            String apodo = InputValidator.inputNombre();
            personaje.setApodo(apodo);

            System.out.println(
                    "¡Magnífico! Ha llegado el momento mágico de tejer el tapiz del destino de su\n" +
                            "campeón. Ante usted se despliegan 25 granos de la fortuna, esferas místicas de\n" +
                            "poder, para distribuir sabiamente entre las cualidades que harán a su héroe o\n" +
                            "heroína legendario/a. ¡Escoja con astucia, pues cada perla es preciosa y cada\n" +
                            "decisión, un eco en el corredor de la eternidad!"
            );

            Integer velocidad = 0, destreza = 0, fuerza= 0, armadura= 0, total= 0;
            int intentos = 3;

            while (intentos > 0) {
                System.out.println("Ingrese la velocidad (1 a 10): ");
                velocidad = InputValidator.inputIntegerRango(1, 10);

                System.out.println("Ingrese la destreza (1 a 5): ");
                destreza = InputValidator.inputIntegerRango(1, 5);

                System.out.println("Ingrese la fuerza (1 a 10): ");
                fuerza = InputValidator.inputIntegerRango(1, 10);

                System.out.println("Ingrese la armadura (1 a 10): ");
                armadura = InputValidator.inputIntegerRango(1, 10);

                total = velocidad + destreza + fuerza + armadura;

                if (total <= 25) {
                    break; // Los puntos se han distribuido correctamente
                } else {
                    System.out.println("La suma total de atributos (" + total +
                            ") excede el máximo permitido de 25. Intentos restantes: " + (intentos - 1));
                    if (intentos > 1) {
                        System.out.println("Por favor, redistribuya los puntos.");
                    }
                }

                intentos--;
                if (intentos == 0) {
                    throw new IllegalArgumentException("Se han excedido los intentos para distribuir los puntos correctamente.");
                }
            }

// Continuar con el resto del código si los puntos se han distribuido correctamente.

            personaje.setVelocidad(velocidad);
            personaje.setDestreza(destreza);
            personaje.setFuerza(fuerza);
            personaje.setArmadura(armadura);

            Integer nivel = calcularNivel(velocidad,destreza,fuerza,armadura);
            personaje.setNivel(nivel);

            //Siempre empieza con salud de 100
            personaje.setSalud(100);

            System.out.println(
                    "Con gran diligencia y un toque de alquimia, las esferas de poder que has\n" +
                            "repartido han tejido la esencia misma de tu valeroso aventurero. Cada grano\n" +
                            "de la fortuna influyó en el tapiz del destino, otorgando a tu campeón un nivel\n" +
                            "que refleja la combinación única de su velocidad, destreza, fuerza y armadura.\n" +
                            "Así, con los hilos del destino ahora entrelazados, el nivel de tu héroe surge...\n" +
                            "¡un espejo de las elecciones forjadas por tu sabia voluntad!"
            );

            System.out.println("Nivel del personaje: " + nivel);
            personajesJugador.add(personaje);

            PrintHelper.imprimirCartas(personajesJugador);
            System.out.println("Presione cualquier tecla para continuar");
            sc.nextLine();sc.nextLine();

        }
        partida.empezarPartidaManual(personajesJugador);

    }

    private static Integer calcularNivel(Integer velocidad, Integer destreza, Integer fuerza, Integer armadura) {
        Integer sumaAtributos = velocidad + destreza + fuerza + armadura;
        Integer maximoPuntos = 10 + 5 + 10 + 10; // 35
        Double nivelNormalizado = (double) sumaAtributos / maximoPuntos;
        return  1 + (int)(nivelNormalizado * 9);
    }
}
