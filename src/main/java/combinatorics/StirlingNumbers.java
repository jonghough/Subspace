package combinatorics;

import java.util.HashMap;

/**
 * Stirling numbers, first and second kind.
 */
public class StirlingNumbers {

    /**
     * Generates the <i>stirling number of the first kind</i>, <i>S(m,n)</i>,
     * for m >= n >= 0.
     * @param m
     * @param n
     * @return
     */
    public static long generateFirstKind(int m, int n) {
        if (m <= 0 || n < 0)
            throw new IllegalArgumentException("Arguments must be positive.");

        HashMap<Integer, Integer> stirlingMap = new HashMap<>();
        stirlingMap.put(generateHash(0, 0), 1);
        for (int i = 0; i <= m; i++) {
            if (i == 0)
                stirlingMap.put(generateHash(0, 1), 0);
            else {
                stirlingMap.put(generateHash(i, 0), 0);
                stirlingMap.put(generateHash(i, i + 1), 0);
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= Math.min(i, n); j++) {
                int f = (i - 1) * stirlingMap.get(generateHash(i - 1, j));

                int g = stirlingMap.get(generateHash(i - 1, j - 1));
                stirlingMap.put(generateHash(i, j), g - f);
            }
        }

        return stirlingMap.get(generateHash(m, n));

    }

    private static int generateHash(int a, int b) {
        int i = a * 31627;
        int j = (b << 1) * 1299721;
        int k = (a << 2) | (b >> 3);
        return i + 13 * (j - k) + b * 233;
    }

}
