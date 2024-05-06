package Assets;

import java.util.*;

public class IndexPersonajes {
    private static Map<String, List<String>> imagenesPorRaza = new HashMap<>();
    private static List<String> nombres = new ArrayList<>();
    private static List<String> apodos = new ArrayList<>();

    public IndexPersonajes() {
        nombres = Arrays.asList(
                "Arya", "Bran", "Cersei", "Daenerys", "Eddard",
                "Frodo", "Gandalf", "Harry", "Ivanhoe", "Jon"
        );
        apodos = Arrays.asList(
                "The Brave", "The Swift", "The Vengeful", "Dragonborn", "The Honorable",
                "Ringbearer", "The Grey", "The Chosen", "The Knight", "The White Wolf"
        );
        imagenesPorRaza.put("Humano", Arrays.asList("human1.png", "human2.png", "human3.png", "human4.png", "human5.png", "human6.png", "human7.png", "human8.png", "human9.png", "human10.png"));
        imagenesPorRaza.put("Elfo", Arrays.asList("elf1.png", "elf2.png", "elf3.png", "elf4.png", "elf5.png", "elf6.png", "elf7.png", "elf8.png", "elf9.png", "elf10.png"));
        imagenesPorRaza.put("Orco", Arrays.asList("orc1.png", "orc2.png", "orc3.png", "orc4.png", "orc5.png", "orc6.png", "orc7.png", "orc8.png", "orc9.png", "orc10.png"));
    }

    public static Map<String, List<String>>  getAllImagenesPorRaza() {
        return imagenesPorRaza;
    }

    public static List<String> getImagenesPorRaza(String raza) {
        return imagenesPorRaza.get(raza);
    }

    public static List<String> getNombres() {
        return nombres;
    }

    public static List<String> getApodos() {
        return apodos;
    }
}
