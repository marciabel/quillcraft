package Model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
