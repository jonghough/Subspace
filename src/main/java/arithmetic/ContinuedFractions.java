
package arithmetic;

/**
 * @author Jon Hough
 */
public class ContinuedFractions {

    /**
     * Continued Fraction array form for Square Root of 2.
     */
    public static final int[] SQRT_2 = {
            1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2
    };

    /**
     * Continued Fraction array form for Phi.
     */
    public static final int[] PHI = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    };

    /**
     * Calculates the continued fraction from the array terms.
     * 
     * @param terms
     * @return
     */
    public static float calculateContinuedFraction(int[] terms) {
        if (terms == null || terms.length == 0) {
            throw new IllegalArgumentException("Bad argument");
        }
        float cf = terms[terms.length - 1];
        for (int i = terms.length - 2; i >= 0; i--) {
            cf = terms[i] + 1.0f / cf;
        }
        return cf;
    }

}
