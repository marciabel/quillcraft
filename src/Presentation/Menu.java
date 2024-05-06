package Presentation;

public abstract class Menu {
    public static void printMenu() {
        System.out.println(
                "|================================================|\n" +
                        "|************************************************|\n" +
                        " _____       _ _ _                 __ _   \n" +
                        "|  _  |     (_) | |               / _| |  \n" +
                        "| | | |_   _ _| | | ___ _ __ __ _| |_| |_ \n" +
                        "| | | | | | | | | |/ __| '__/ _` |  _| __|\n" +
                        "\\ \\/' / |_| | | | | (__| | | (_| | | | |_ \n" +
                        " \\_/\\_\\\\__,_|_|_|_|\\___|_|  \\__,_|_|  \\__|\n" +
                        "|************************************************|\n" +
                        "|================================================|\n" +
                        "\n" +
                        "               GAME MENU\n" +
                        "\n" +
                        "[1] Start a new game\n" +
                        "[2] Start a new custom game\n" +
                        "[3] Read logs\n" +
                        "[4] Delete logs\n" +
                        "[5] Exit\n" +
                        "\n" +
                        "Please select an option by entering the corresponding number:\n"
        );
    }


}
