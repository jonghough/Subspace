package combinatorics;

import java.util.HashMap;

/**
 * Stirling numbers, first and second kind.
 */
public class StirlingNumbers {

    /**
     * Generates the <i>stirling number of the first kind</i>, <i>S(m,n)</i>,
     * for m >= n >= 0.
     * <br>
     * An interpretation of <i>unsigned stirling numbers of the first kind</i> is the number
     * of permutations of an <i>m</i> element set with exactly <i>n</i> cycles.<br>
     * Hence <i>S(m,m) = 1</i>, because there is only one way to arrange an <i>m</i>-element
     * set as as m-cycles.
     * <br>
     * <i></i>Signed stirling numbers of the first kind</i> are defined as <i>(-1)^(m-n)*S(m,n)</i>.
     * @param m size of set
     * @param n number of cycles
     * @param signed true if signed stirling numbers, false if unsigned.
     * @return stirling number of the first kind.
     */
    public static long generateFirstKind(int m, int n, boolean signed) {
        if (m <= 0 || n < 0)
            throw new IllegalArgumentException("Arguments must be positive.");

        HashMap<Integer, Long> stirlingMap = new HashMap<>();
        stirlingMap.put(generateHash(0, 0), 1L);
        for (int i = 0; i <= m; i++) {
            if (i == 0)
                stirlingMap.put(generateHash(0, 1), 0L);
            else {
                stirlingMap.put(generateHash(i, 0), 0L);
                stirlingMap.put(generateHash(i, i + 1), 0L);
            }
        }
        if(!signed) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= Math.min(i, n); j++) {
                    long f = j * stirlingMap.get(generateHash(i - 1, j));

                    long g = stirlingMap.get(generateHash(i - 1, j - 1));
                    stirlingMap.put(generateHash(i, j), g + f);
                }
            }
        }
        else{
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= Math.min(i, n); j++) {
                    long f = (i - 1) * stirlingMap.get(generateHash(i - 1, j));

                    long g = stirlingMap.get(generateHash(i - 1, j - 1));
                    stirlingMap.put(generateHash(i, j), g - f);
                }
            }
        }

        return stirlingMap.get(generateHash(m, n));

    }

    private static int generateHash(int a, int b) {
        int i = a * 31627;
        int j = (b << 1) * 1299721;
        int k = (a << 2) | (b >> 3);
        int l = a^(b<<1);
        return i + 13 * (j - k) + b * 233 +l;
    }

}
