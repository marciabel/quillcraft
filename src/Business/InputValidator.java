package Business;

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
                }
            } catch (NumberFormatException e) {
                System.out.println("Input inválido. Por favor, ingrese un número válido:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar una opción válida.");
    }

    public static String inputNombre() {
        int intentos = 3;
        while (intentos > 0) {
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 4) {
                return nombre;
            } else {
                System.out.println("El nombre debe tener al menos 4 caracteres. Por favor, intente de nuevo:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar un nombre válido.");
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
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar un número positivo.");
    }

    public static Integer inputIntegerRango(Integer min, Integer max) {
        int intentos = 3;
        while (intentos > 0) {
            try {
                Integer numero = Integer.parseInt(sc.nextLine().trim());
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar un número en el rango.");
    }


    public static String inputRaza() {
        int intentos = 3;
        while (intentos > 0) {
            String raza = sc.nextLine().trim().toUpperCase();
            if (raza.equals("HUMANO") || raza.equals("ORCO") || raza.equals("ELFO")) {
                return raza;
            } else {
                System.out.println("Raza inválida. Por favor, ingrese HUMANO, ORCO o ELFO:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar una raza válida.");
    }

    public static Integer inputEdad() {
        int intentos = 3;
        while (intentos > 0) {
            try {
                Integer edad = Integer.parseInt(sc.nextLine().trim());
                if (edad >= 0 && edad <= 300) {
                    return edad;
                } else {
                    System.out.println("Edad inválida. Por favor, ingrese una edad entre 0 y 300:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido para la edad:");
            }
            intentos--;
        }
        throw new IllegalArgumentException("Se han excedido los intentos para ingresar una edad válida.");
    }
}
