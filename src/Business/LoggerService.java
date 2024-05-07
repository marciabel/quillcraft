package Business;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class LoggerService {
    private static final Logger logger = Logger.getLogger(LoggerService.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("game.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al configurar el logger", e);
        }
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }

    // Registrar un mensaje de información
    public static void info(String message) {
        log(Level.INFO, message);
    }

    // Registrar un mensaje de error
    public static void error(String message) {
        log(Level.SEVERE, message);
    }

    public static void getLogFile(String logFilePath) {
        File logFile = new File(logFilePath);

        try (Scanner scanner = new Scanner(logFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo de log no se encontró: " + e.getMessage());
        }
    }

    public static void deleteLogs() {
        String logFilePath = "game.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, false))) {
            // Al no escribir nada en el archivo, se borrará su contenido
        } catch (IOException e) {
            System.err.println("No se pudo borrar el contenido del archivo de log: " + e.getMessage());
        }
    }
}