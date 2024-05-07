package Business;

import Model.Jugador;
import Model.Personaje;
import Presentation.Console.PrintHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartidaService {
    private Scanner sc = new Scanner(System.in);

    private List<Jugador> jugadores = new ArrayList<>();

    private Jugador jugador;
    private Jugador maquina;

    private Integer numeroRonda = 0;
    private Jugador jugadorIniciadorDeRonda;

    public PartidaService(String nombreJugador) {
        this.jugador = new Jugador(nombreJugador);
        this.maquina = new Jugador("Maquina");

        jugadores.add(jugador);
        jugadores.add(maquina);

        PrintHelper.imprimirInicioPartida();
    }

    public void empezarPartidaAleatoria() {
        inicializarPersonajes();

        //Comprobar que las listas de personajes han sido populadas
        if (jugador.getPersonajes().isEmpty() || maquina.getPersonajes().isEmpty()) {
            throw new IllegalStateException("Los jugadores deben tener por lo menos un personaje para poder jugar");
        }

        empezarPartida();
    }

    public void empezarPartidaManual(List<Personaje> personajesJugador) {
        inicializarPersonajes();

        //A fines de reutilizar codigo y para simplificar la implementacion del programa, se recurre a este metodo
        //Sin embargo, idealmente no se crearian personajes solo para luego borrarlos
        jugador.getPersonajes().clear();
        jugador.setPersonajes(personajesJugador);

        empezarPartida();
    }

    public void empezarPartida() {
        while (!(jugador.getPersonajes().isEmpty() || maquina.getPersonajes().isEmpty())) {
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- CARTAS DEL JUGADOR " + jugador.nombre.toUpperCase() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            PrintHelper.imprimirCartas(jugador.getPersonajes());
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- CARTAS DEL JUGADOR " + maquina.nombre.toUpperCase() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            PrintHelper.imprimirCartas(maquina.getPersonajes());
            System.out.println("Presione cualquier tecla para continuar");
            sc.nextLine();

            // RONDA
            Personaje personajeJugadorUno =
                    jugador.getPersonajes().get(NumberGenerator.generateRandomPositiveInteger(1, jugador.getPersonajes().size())-1);

            Personaje personajeJugadorDos =
                    maquina.getPersonajes().get(NumberGenerator.generateRandomPositiveInteger(1, maquina.getPersonajes().size())-1);

            confrontar(personajeJugadorUno, personajeJugadorDos);
        }

        Jugador jugadorGanador = jugador.equals(jugadorIniciadorDeRonda)? maquina : jugador;
        PrintHelper.imprimirFinDeLaPartida(jugadorGanador.nombre);
    }

    public List<Jugador> inicializarPersonajes() {
        for (Jugador jugador :jugadores) {
            for (int i = 0; i < 3; i++) {
                Personaje newPersonaje = PersonajeGenerator.generarPersonajeAleatorio();
                jugador.addPersonaje(newPersonaje);
            }
        }
        return jugadores;
    }

//    public void iniciarRonda() {
//        while (!(jugador.getPersonajes().isEmpty() || maquina.getPersonajes().isEmpty())) {
//            // RONDA
//            Personaje personajeJugadorUno =
//                    jugador.getPersonajes().get(NumberGenerator.generateRandomPositiveInteger(1, jugador.getPersonajes().size())-1);
//
//            Personaje personajeJugadorDos =
//                    maquina.getPersonajes().get(NumberGenerator.generateRandomPositiveInteger(1, maquina.getPersonajes().size())-1);
//        }
//
//    }

    private void confrontar(Personaje p1, Personaje p2) {
        numeroRonda += 1;
        PrintHelper.imprimirRonda(numeroRonda);

        List<Personaje> personajesCompetidores = new ArrayList<>();
        personajesCompetidores.add(p1);
        personajesCompetidores.add(p2);
        PrintHelper.imprimirCartas(personajesCompetidores);

        Integer numeroAtaque = 0;
        Personaje personajeAtacante, personajeDefensor;

        if (numeroRonda == 1) {
            jugadorIniciadorDeRonda = jugadores.get(NumberGenerator.generateRandomPositiveInteger(1, jugadores.size()) - 1);
        }
        personajeAtacante = jugadorIniciadorDeRonda.getPersonajes().contains(p1) ? p1 : p2;
        personajeDefensor = personajeAtacante == p1 ? p2 : p1;

        System.out.println("Jugador iniciador de la ronda: " + jugadorIniciadorDeRonda.nombre);

        while (numeroAtaque < 7 && personajeDefensor.getSalud() > 0 && personajeAtacante.getSalud() > 0) {
            int daño = personajeAtacante.atacar(personajeDefensor.poderDefensa());
            personajeDefensor.setSalud(personajeDefensor.getSalud() - daño);
            System.out.println("Ataque número " + (numeroAtaque + 1) + ": " + personajeAtacante.getNombre() + " inflige " + daño + " de daño a " + personajeDefensor.getNombre());
            System.out.println("Salud de " + personajeDefensor.getNombre() + " después del ataque: " + personajeDefensor.getSalud());

            if (personajeDefensor.getSalud() <= 0) {
                System.out.println(personajeDefensor.getNombre() + " ha sido derrotado!");
                break;
            }

            // Intercambiar roles de atacante y defensor
            Personaje temp = personajeAtacante;
            personajeAtacante = personajeDefensor;
            personajeDefensor = temp;

            numeroAtaque++;
        }

        if (personajeDefensor.getSalud() <= 0 || personajeAtacante.getSalud() <= 0) {
            Personaje personajeGanador = personajeDefensor.getSalud() <= 0 ? personajeAtacante : personajeDefensor;
            Personaje personajePerdedor = personajeGanador == personajeAtacante ? personajeDefensor : personajeAtacante;

            for (Jugador jugador : jugadores) {
                if (jugador.getPersonajes().contains(personajePerdedor)) {
                    System.out.println("Jugador perdedor de la ronda: " + jugador.nombre);
                    jugador.getPersonajes().remove(personajePerdedor);
                    jugadorIniciadorDeRonda = jugador;
                    System.out.println("Personaje " + personajePerdedor.getNombre() + " eliminado del jugador " + jugador.nombre);
                }
                if (jugador.getPersonajes().contains(personajeGanador)) {
                    System.out.println("Jugador ganador de la ronda: " + jugador.nombre);

                    personajeGanador.setSalud(personajeGanador.getSalud() + 1000);
                    System.out.println("Salud de " + personajeGanador.getNombre() + " incrementada a " + personajeGanador.getSalud() + " por la victoria");
                }
            }
        }
    }
}
