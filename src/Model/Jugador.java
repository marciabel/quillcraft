package Model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    public String nombre;
    private List<Personaje> personajes;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "personajes=" + personajes +
                '}';
    }

    public void addPersonaje(Personaje personaje) {
        this.personajes.add(personaje);
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
