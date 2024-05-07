package Business;

import Assets.IndexPersonajes;
import Model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonajeGenerator {

    public static Personaje generarPersonajeAleatorio() throws ParseException {
        IndexPersonajes indexPersonajes = new IndexPersonajes();
        List<String> nombres= indexPersonajes.getNombres();
        List<String> apodos= indexPersonajes.getApodos();
        List<String> imagenesPorRaza = List.of();

        Personaje personaje = null;

        //Elegir raza random
        Integer razaRandom = NumberGenerator.generateRandomPositiveInteger(1,3);
        imagenesPorRaza = switch (razaRandom) {
            case 1 -> {
                personaje = new Humano();
                yield indexPersonajes.getImagenesPorRaza("Humano");
            }
            case 2 -> {
                personaje = new Elfo();
                yield indexPersonajes.getImagenesPorRaza("Elfo");
            }
            case 3 -> {
                personaje = new Orco();
                yield indexPersonajes.getImagenesPorRaza("Orco");
            }
            default -> imagenesPorRaza;
        };

        //Rellenar atributos de manera aleatoria
        //Nombre
        String nombrePersonaje = nombres.get(NumberGenerator.generateRandomPositiveInteger(1, nombres.size())-1);
        personaje.setNombre(nombrePersonaje);
        //Apodo
        personaje.setApodo(apodos.get(NumberGenerator.generateRandomPositiveInteger(1, apodos.size())-1));
        //Edad
        personaje.setEdad(NumberGenerator.generateRandomPositiveInteger(1, 300));
        //FechaNacimiento
        // Nota: Se simplifico asumiendo que todos los meses tienen 30 dias y asumiendo el anio completo
        personaje.setFechaNacimiento(generarFechaNacimiento());
        personaje.setEdad(calcularEdad(personaje.getFechaNacimiento()));
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

        return personaje;
    }

    public static Personaje generarPersonajeManual() {

        System.out.println("Creacion del proximo personaje: ");
        Personaje personaje = null;
        Integer granosFortuna = 25;

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

            System.out.println("Ingrese un apodo para el personaje: ");
            String apodo = InputValidator.inputNombre();
            personaje.setApodo(apodo);

            System.out.println("Ingrese la fecha de nacimiento del personaje:  ");
            Date fechaNacimiento = InputValidator.inputDate("dd/MM/yyyy");
            personaje.setFechaNacimiento(fechaNacimiento);
            personaje.setEdad(PersonajeGenerator.calcularEdad(fechaNacimiento));

            System.out.println(
                    "¡Magnífico! Ha llegado el momento mágico de tejer el tapiz del destino de su\n" +
                            "campeón. Ante usted se despliegan 25 granos de la fortuna, esferas místicas de\n" +
                            "poder, para distribuir sabiamente entre las cualidades que harán a su héroe o\n" +
                            "heroína legendario/a. ¡Escoja con astucia, pues cada perla es preciosa y cada\n" +
                            "decisión, un eco en el corredor de la eternidad!"
            );

            Integer velocidad = 0, destreza = 0, fuerza= 0, armadura= 0;
            int intentos = 3;

            while (intentos > 0) {
                System.out.println("Tiene " + granosFortuna + " granos de la fortuna para gastar");
                System.out.println("Ingrese la velocidad (1 a 10): ");
                velocidad = InputValidator.inputIntegerRango(1, 10);
                granosFortuna -=  velocidad;

                System.out.println("Tiene " + granosFortuna + " granos de la fortuna para gastar");
                System.out.println("Ingrese la destreza (1 a 5): ");
                destreza = InputValidator.inputIntegerRango(1, 5);
                granosFortuna -= destreza;

                System.out.println("Tiene " + granosFortuna + " granos de la fortuna para gastar");
                System.out.println("Ingrese la fuerza (1 a 10): ");
                fuerza = InputValidator.inputIntegerRango(1, 10);
                granosFortuna -= fuerza;

                System.out.println("Tiene " + granosFortuna + " granos de la fortuna para gastar");
                System.out.println("Ingrese la armadura (1 a 10): ");
                armadura = InputValidator.inputIntegerRango(1, 10);
                granosFortuna -= armadura;

                if (granosFortuna >= 0) {
                    break; // Los puntos se han distribuido correctamente
                } else {
                    System.out.println("La suma total de atributos (" + ((granosFortuna*-1)+25) +
                            ") excede el máximo permitido de 25. Intentos restantes: " + (intentos - 1));
                    if (intentos > 1) {
                        System.out.println("Por favor, redistribuya los puntos.");
                        granosFortuna = 25;
                    }
                }

                intentos--;
                if (intentos == 0) {
                    throw new IllegalArgumentException("Se han excedido los intentos para distribuir los puntos correctamente.");
                }
            }

            personaje.setVelocidad(velocidad);
            personaje.setDestreza(destreza);
            personaje.setFuerza(fuerza);
            personaje.setArmadura(armadura);

            Integer nivel = calcularNivel(velocidad,destreza,fuerza,armadura);
            personaje.setNivel(nivel);

            //Siempre empieza con salud de 100
            personaje.setSalud(100);

            if(personaje.hasNullAttribute()) {
                System.out.println("Alguno de los atributos ingresados no es correcto y fallo la carga de personajes manual.");
                LoggerService.error("No se pudo inicializar el personaje " + personaje );
                return null;
            }

            System.out.println(
                    "Con gran diligencia y un toque de alquimia, las esferas de poder que has\n" +
                            "repartido han tejido la esencia misma de tu valeroso aventurero. Cada grano\n" +
                            "de la fortuna influyó en el tapiz del destino, otorgando a tu campeón un nivel\n" +
                            "que refleja la combinación única de su velocidad, destreza, fuerza y armadura.\n" +
                            "Así, con los hilos del destino ahora entrelazados, el nivel de tu héroe surge...\n" +
                            "¡un espejo de las elecciones forjadas por tu sabia voluntad!"
            );

            System.out.println("Nivel del personaje: " + nivel);

            return personaje;
    }

    private static Integer calcularNivel(Integer velocidad, Integer destreza, Integer fuerza, Integer armadura) {
        Integer sumaAtributos = velocidad + destreza + fuerza + armadura;
        Integer maximoPuntos = 10 + 5 + 10 + 10; // 35
        Double nivelNormalizado = (double) sumaAtributos / maximoPuntos;
        return  1 + (int)(nivelNormalizado * 9);
    }

    private static Date generarFechaNacimiento() throws ParseException {
        Integer anio = NumberGenerator.generateRandomPositiveInteger(2023-300, 2023);
        Integer mes = NumberGenerator.generateRandomPositiveInteger(1, 12);
        Integer dia = NumberGenerator.generateRandomPositiveInteger(1, 30);
        String fecha = "";

        if (mes == 2) {
            dia = NumberGenerator.generateRandomPositiveInteger(1, 28);
        }
        if (dia < 10) {fecha = fecha + "0" + dia + "/";}
        else { fecha = fecha + dia + "/";}
        if (mes < 10) {fecha = fecha + "0" + mes + "/";}
        else { fecha = fecha + mes + "/";}
        fecha = fecha + anio;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date = dateFormat.parse(fecha);
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(date);
        return date;
    }

    public static Integer calcularEdad(Date fechaNacimiento) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaNacimiento);

        Calendar todayCal = Calendar.getInstance();

        int anios = todayCal.get(Calendar.YEAR) - calendario.get(Calendar.YEAR);

        if (calendario.get(Calendar.MONTH) > todayCal.get(Calendar.MONTH) ||
                (calendario.get(Calendar.MONTH) == todayCal.get(Calendar.MONTH) &&
                        calendario.get(Calendar.DAY_OF_MONTH) > todayCal.get(Calendar.DAY_OF_MONTH))) {
            anios--;
        }

        return anios;
    }
}
