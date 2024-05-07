package Business;

import Model.*;
import Presentation.Console.PrintHelper;

import java.text.ParseException;
import java.util.*;

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
    }

    public void empezarPartidaAleatoria() {
        inicializarPersonajes();

        //Comprobar que las listas de personajes han sido populadas
        if (jugador.getPersonajes().isEmpty() || maquina.getPersonajes().isEmpty()) {
            throw new IllegalStateException("Los jugadores deben tener por lo menos un personaje para poder jugar");
        }

        empezarPartida();
    }

    public void empezarPartidaManual() throws ParseException {
        //Inicializar personajes manual
        for (int i = 0; i < 3; i++) {
            maquina.addPersonaje(PersonajeGenerator.generarPersonajeAleatorio());
        }
        List<Personaje> personajesCreadosManualmente = inicializarPersonajesManual();
        if (personajesCreadosManualmente.size() < 3) {
            System.out.println("No se pudieron inicializar correctamente los personajes. Intente nuevamente");
            LoggerService.error("No se pudieron inicializar correctamente los personajes. Se cancela la partida manual");
            return;
        }
        jugador.setPersonajes(personajesCreadosManualmente);

        empezarPartida();
    }

    private List<Jugador> inicializarPersonajes() {
        for (Jugador jugador :jugadores) {
            for (int i = 0; i < 3; i++) {
                Personaje newPersonaje = null;
                try {
                    newPersonaje = PersonajeGenerator.generarPersonajeAleatorio();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                jugador.addPersonaje(newPersonaje);
            }
        }
        return jugadores;
    }

    private List<Personaje> inicializarPersonajesManual() {
        List<Personaje> personajesJugador = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LoggerService.info("Creacion de personajes manual: (" + (i+1) + "/3)");
            Personaje personaje = PersonajeGenerator.generarPersonajeManual();
            if (personaje == null) {
                break;
            }
            personajesJugador.add(personaje);
            LoggerService.info("Se creo el siguiente personaje: " + personaje);

            PrintHelper.imprimirCartas(personajesJugador);
            System.out.println("Presione cualquier tecla para continuar");
            sc.nextLine();
        }
        return (personajesJugador);
    }

    private void empezarPartida() {
        PrintHelper.imprimirInicioPartida();
        System.out.println("Bienvenido al amanecer de la Épica Batalla de Destinos, donde te enfrentarás a la astucia de la Maquinaria Arcana.");
        System.out.println();
        System.out.println("Presione cualquier tecla para repartir las cartas ");
        sc.nextLine();
        System.out.println("El destino ha tejido su hilo; recibirás tus cartas al azar, sin elección, como dicta el capricho del azar etéreo.");

        while (!(jugador.getPersonajes().isEmpty() || maquina.getPersonajes().isEmpty())) {
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- CARTAS DEL JUGADOR " + jugador.getNombre().toUpperCase() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            LoggerService.info("Cartas del jugador: " + jugador.getNombre().toLowerCase() + jugador.getPersonajes().toString());
            PrintHelper.imprimirCartas(jugador.getPersonajes());

            System.out.println("Las cartas de tu rival, la Maquinaria Arcana, han sido forjadas y te esperan en el campo de batalla.");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- CARTAS DEL JUGADOR " + maquina.getNombre().toUpperCase() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            LoggerService.info("Cartas del jugador: " + maquina.getNombre().toLowerCase() + maquina.getPersonajes().toString());
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
        System.out.println("El polvo se asienta y las leyendas reconocen a un nuevo vencedor; ¡la victoria ha sido reclamada!");
        System.out.println("Presione cualquier tecla para continuar");
        sc.nextLine();
        PrintHelper.imprimirFinDeLaPartida(jugadorGanador.getNombre());
        LoggerService.info("Jugador ganador de la partida " + jugadorGanador.getNombre());
        System.out.println("Presione cualquier tecla para volver al menu principal");
        sc.nextLine();
    }

    private void confrontar(Personaje p1, Personaje p2) {
        numeroRonda += 1;
        PrintHelper.imprimirRonda(numeroRonda);
        System.out.println("Presione cualquier tecla para continuar");
        sc.nextLine();
        LoggerService.info("Inicia la ronda numero " + numeroRonda);

        List<Personaje> personajesCompetidores = new ArrayList<>();
        personajesCompetidores.add(p1);
        personajesCompetidores.add(p2);

        LoggerService.info("Los personajes a competir son " + personajesCompetidores);
        PrintHelper.imprimirInicioConfrontacion();
        PrintHelper.imprimirCartas(personajesCompetidores);
        System.out.println("Presione cualquier tecla para continuar");
        sc.nextLine();

        Integer numeroAtaque = 0;
        Personaje personajeAtacante, personajeDefensor;

        if (numeroRonda == 1) {
            jugadorIniciadorDeRonda = jugadores.get(NumberGenerator.generateRandomPositiveInteger(1, jugadores.size()) - 1);
        }
        personajeAtacante = jugadorIniciadorDeRonda.getPersonajes().contains(p1) ? p1 : p2;
        personajeDefensor = personajeAtacante == p1 ? p2 : p1;

        LoggerService.info("Jugador iniciador de la ronda: " + jugadorIniciadorDeRonda.getNombre());
        LoggerService.info("El personaje atacante es " + personajeAtacante);
        LoggerService.info("El personaje que defiende es  " + personajeDefensor);

        System.out.println("Jugador iniciador de la ronda: " + jugadorIniciadorDeRonda.getNombre());

        while (numeroAtaque < 7 && personajeDefensor.getSalud() > 0 && personajeAtacante.getSalud() > 0) {
            int daño = personajeAtacante.atacar(personajeDefensor.poderDefensa());
            personajeDefensor.setSalud(personajeDefensor.getSalud() - daño);
            System.out.println("### Ataque número [" + (numeroAtaque + 1) + "]: " + personajeAtacante.getNombre() + " inflige " + daño + " de daño a " + personajeDefensor.getNombre());
            System.out.println("Salud de " + personajeDefensor.getNombre() + " después del ataque: " + personajeDefensor.getSalud());

            LoggerService.info("Ataque número " + (numeroAtaque + 1) + ": " + personajeAtacante.getNombre() + " inflige " + daño + " de daño a " + personajeDefensor.getNombre());
            LoggerService.info("Salud de " + personajeDefensor.getNombre() + " después del ataque: " + personajeDefensor.getSalud());

            if (personajeDefensor.getSalud() <= 0) {
                System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ " + personajeDefensor.getNombre() + " ha sido derrotado! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
                LoggerService.info(personajeDefensor.getNombre() + " ha sido derrotado y se ha eliminado! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                break;
            }

            // Intercambiar roles de atacante y defensor
            Personaje temp = personajeAtacante;
            personajeAtacante = personajeDefensor;
            personajeDefensor = temp;

            numeroAtaque++;
        }

        //Validar si alguno de los dos tiene salud menor que 0, sino la partida sigue normal.
        if (personajeDefensor.getSalud() <= 0 || personajeAtacante.getSalud() <= 0) {
            Personaje personajeGanador = personajeDefensor.getSalud() <= 0 ? personajeAtacante : personajeDefensor;
            Personaje personajePerdedor = personajeGanador == personajeAtacante ? personajeDefensor : personajeAtacante;

            for (Jugador jugador : jugadores) {
                if (jugador.getPersonajes().contains(personajePerdedor)) {
                    System.out.println("Jugador perdedor de la ronda: " + jugador.getNombre());
                    LoggerService.info("Jugador perdedor de la ronda: " + jugador.getNombre());
                    jugador.getPersonajes().remove(personajePerdedor);
                    jugadorIniciadorDeRonda = jugador;
                    LoggerService.info("Personaje " + personajePerdedor.getNombre() + " eliminado del jugador " + jugador.getNombre());
                }
                if (jugador.getPersonajes().contains(personajeGanador)) {
                    personajeGanador.setSalud(personajeGanador.getSalud() + 1000);
                    System.out.println("ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆. Salud de " + personajeGanador.getNombre() + " incrementada a " + personajeGanador.getSalud() + " por la victoria ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.˚˚ ༘ ೀ⋆.");
                    LoggerService.info("Salud de " + personajeGanador.getNombre() + " incrementada a " + personajeGanador.getSalud() + " por la victoria");

                    System.out.println("Jugador ganador de la ronda: " + jugador.getNombre());
                    LoggerService.info("Jugador ganador de la ronda: " + jugador.getNombre());
                }
            }
        }
        System.out.println("Presione cualquier tecla para continuar");
        sc.nextLine();
    }
}
