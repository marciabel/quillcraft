package Business;

import Assets.IndexPersonajes;
import Model.*;

import java.util.List;

public class PersonajeGenerator {

    public static Personaje generarPersonajeAleatorio() {
        IndexPersonajes indexPersonajes = new IndexPersonajes();
        List<String> nombres= indexPersonajes.getNombres();
        List<String> apodos= indexPersonajes.getApodos();
        List<String> imagenesPorRaza = List.of();

        Personaje personaje = null;

        //Elegir raza random
        Integer razaRandom = NumberGenerator.generateRandomPositiveInteger(1,3);
        System.out.println("Raza random: " + razaRandom);
        imagenesPorRaza = switch (razaRandom) {
            case 1 -> {
                personaje = new Humano();
                System.out.println("Personaje " + personaje);
                yield indexPersonajes.getImagenesPorRaza("Humano");
            }
            case 2 -> {
                personaje = new Elfo();
                System.out.println("Personaje " + personaje);
                yield indexPersonajes.getImagenesPorRaza("Elfo");
            }
            case 3 -> {
                personaje = new Orco();
                System.out.println("Personaje " + personaje);
                yield indexPersonajes.getImagenesPorRaza("Orco");
            }
            default -> imagenesPorRaza;
        };

        //Rellenar atributos de manera aleatoria
        //Nombre
        String nombrePersonaje = nombres.get(NumberGenerator.generateRandomPositiveInteger(1, nombres.size())-1);
        System.out.println("Nombre personaje; " + nombrePersonaje);
        personaje.setNombre(nombrePersonaje);
        //Apodo
        personaje.setApodo(apodos.get(NumberGenerator.generateRandomPositiveInteger(1, apodos.size())-1));
        //Edad
        personaje.setEdad(NumberGenerator.generateRandomPositiveInteger(1, 300));
        //FechaNacimiento
        // Nota: Se simplifico asumiendo que todos los meses tienen 30 dias y asumiendo el anio completo
        Fecha fechaNacimiento = new Fecha();
        fechaNacimiento.setAnio(2024- personaje.getEdad());
        fechaNacimiento.setMes(NumberGenerator.generateRandomPositiveInteger(1, 12));
        fechaNacimiento.setDia(NumberGenerator.generateRandomPositiveInteger(1, 30));
        personaje.setFechaNacimiento(fechaNacimiento);
        //Salud - Siempre empieza con 100 de salud
        personaje.setSalud(100);
        //Imagen
        personaje.setImagen(imagenesPorRaza.get(NumberGenerator.generateRandomPositiveInteger(1, imagenesPorRaza.size())-1));

        //Velocidad
        personaje.setVelocidad(NumberGenerator.generateRandomPositiveInteger(1, 10));
        //Destreza
        personaje.setDestreza(NumberGenerator.generateRandomPositiveInteger(1, 5));
        //Fuerza
        personaje.setFuerza(NumberGenerator.generateRandomPositiveInteger(1, 10));
        //Nivel
        personaje.setNivel(NumberGenerator.generateRandomPositiveInteger(1, 10));
        //Armadura
        personaje.setArmadura(NumberGenerator.generateRandomPositiveInteger(1, 10));

        System.out.println("Personaje " + personaje);
        return personaje;
    }
}
