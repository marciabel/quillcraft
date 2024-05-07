package Business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InputValidator {
    private static Scanner sc = new Scanner(System.in);

    public static String inputOpcionMenu() {
        int intentos = 3;
        while (intentos > 0) {
            try {
                Integer opcionMenu = Integer.parseInt(sc.nextLine().trim());
                if (opcionMenu >= 1 && opcionMenu <= 4) {
                    return opcionMenu.toString();
                } else {
                    System.out.println("Opcion invalida. Por favor, ingrese un número entre 1 y 4:");
                    LoggerService.error("Se ingreso una opcion de menu invalida (" + opcionMenu + ")" );
                }
            } catch (NumberFormatException e) {
                System.out.println("Input inválido. Por favor, ingrese un número válido:");
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar una opción válida.");
        LoggerService.error("Se han excedido los intentos para ingresar una opción válida." );
        return null;
    }

    public static String inputNombre() {
        int intentos = 3;
        while (intentos > 0) {
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 4) {
                return nombre;
            } else {
                System.out.println("El nombre debe tener al menos 4 caracteres. Por favor, intente de nuevo:");
                LoggerService.error("Se ingreso un nombre invalido (" + nombre + ")" );
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar una opción válida.");
        LoggerService.error("Se han excedido los intentos para ingresar una opción válida." );
        return null;
    }

    public static Integer inputIntegerPositivo() {
        int intentos = 3;
        while (intentos > 0) {
            try {
                Integer numero = Integer.parseInt(sc.nextLine().trim());
                if (numero >= 0) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número positivo:");
                    LoggerService.error("Se ingreso un numero invalido (" + numero + ")" );
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido:");
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar una opción válida.");
        LoggerService.error("Se han excedido los intentos para ingresar una opción válida." );
        return null;
    }

    public static Integer inputIntegerRango(Integer min, Integer max) {
        int intentos = 3;
        while (intentos > 0) {
            try {
                Integer numero = Integer.parseInt(sc.nextLine().trim());
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    LoggerService.error("Se ingreso un numero invalido (" + numero + ")" );
                    System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido:");
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar un número en el rango.");
        LoggerService.error("Se han excedido los intentos para ingresar un número en el rango." );
        return null;
    }

    public static String inputRaza() {
        int intentos = 3;
        while (intentos > 0) {
            String raza = sc.nextLine().trim().toUpperCase();
            if (raza.equals("HUMANO") || raza.equals("ORCO") || raza.equals("ELFO")) {
                return raza;
            } else {
                System.out.println("Raza inválida. Por favor, ingrese HUMANO, ORCO o ELFO:");
                LoggerService.error("Se ingreso una raza invalida (" + raza + ")" );
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar una raza válida.");
        LoggerService.error("Se han excedido los intentos para ingresar una raza válida." );
        return null;
    }

    public static Integer inputEdad() {
        int intentos = 3;
        Integer edad = null;
        while (intentos > 0) {
            try {
                edad = Integer.parseInt(sc.nextLine().trim());
                if (edad >= 0 && edad <= 300) {
                    return edad;
                } else {
                    System.out.println("Edad inválida. Por favor, ingrese una edad entre 0 y 300:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido para la edad:");
                LoggerService.error("Se ingreso una edad invalida (" + edad + ")" );
            }
            intentos--;
            if (intentos == 0 ) {return null;}
        }
        System.out.println("Se han excedido los intentos para ingresar una edad válida.");
        LoggerService.error("Se han excedido los intentos para ingresar una edad válida." );
        return null;
    }

    public static Date inputDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false); // Esto hace que el análisis de la fecha sea estricto
        Calendar currentDate = Calendar.getInstance();
        Calendar earliestDate = Calendar.getInstance();
        earliestDate.add(Calendar.YEAR, -300); // Fecha 300 años en el pasado

        int intentos = 3;
        while (intentos > 0) {
            System.out.println("Por favor, ingrese la fecha en el formato " + format + " (no más de 300 años en el pasado):");
            String dateInput = sc.nextLine().trim();
            try {
                Date date = dateFormat.parse(dateInput);
                Calendar inputDate = Calendar.getInstance();
                inputDate.setTime(date);

                if (inputDate.after(earliestDate) && inputDate.before(currentDate)) {
                    return date; // Fecha válida y dentro del rango permitido
                } else {
                    System.out.println("La fecha ingresada no puede tener más de 300 años de antigüedad.");
                }
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Por favor, intente nuevamente.");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar una fecha válida.");
    }
}
