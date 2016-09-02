package transcendence;

import java.math.BigDecimal;
/**
 * Beta function,
 * Beta(x,y) = Gamma(x) * Gamma(y) / Gamma(x + y), <br>
 * defined for real numbers, x, y, greater than 0.
 *
 */
public class Beta {

    /**
     * Calculates an approximation of Beta(x,y), using Lanczos approx function.
     * @param x first parameter
     * @param y second parameter
     * @return approximation of Beta(x,y)
     */
    public static BigDecimal beta(BigDecimal x, BigDecimal y){
        if(x.compareTo(BigDecimal.ZERO) <= 0 || y.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Arguments must be positive");
        BigDecimal gammax = Gamma.lanczosApproximation(x);
        BigDecimal gammay = Gamma.lanczosApproximation(y);
        BigDecimal gxpy = Gamma.lanczosApproximation(x.add(y));

        return gammax.multiply(gammay).divide(gxpy, 20, BigDecimal.ROUND_HALF_DOWN);
    }

}
