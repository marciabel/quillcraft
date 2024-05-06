package Business;

import java.util.Random;

public class NumberGenerator {
    static Random random = new Random();

    public static Integer generateRandomPositiveInteger(Integer minBound, Integer maxBound) {
        if (maxBound == minBound) {
            return  maxBound;
        }
        if (minBound < 0 || maxBound < minBound) {
            throw new IllegalArgumentException("Invalid bounds for random integer generation.");
        }
        Random random = new Random();
        return random.nextInt(maxBound - minBound + 1) + minBound;
    }
}
