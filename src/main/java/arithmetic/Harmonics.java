package arithmetic;

/**
 *
 */
public class Harmonics {

    /**
     * Calculates the <i>nth harmonic number</i>, <i>H(n)</i>.
     * @param n
     * @return
     */
    public static double harmonic(int n){
        if(n <= 0)
            throw new IllegalArgumentException("Argument must be positive.");

        if(n == 1)
            return 1.0;

        double h = 0.0;
        for (int i = 1; i <= n; i++){
            h += 1.0 / i;
        }
        return h;
    }

    public static double alternatingHarmonic(int n){
            if(n <= 0)
                throw new IllegalArgumentException("Argument must be positive.");

            if(n == 1)
                return 1.0;

        double ah = 0.0;
        for(int i = 1; i <= n; i++){
            double tmp = 1.0 / i;
            if(i % 2 == 0)
                tmp *= -1;
            ah += tmp;
        }
        return ah;
    }
}
