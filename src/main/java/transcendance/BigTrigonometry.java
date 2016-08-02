package transcendance;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Utility functions for calculating trigonometric functions for <code>BigDecimal</code> values.
 *
 */
public class BigTrigonometry {

    public static final BigDecimal TWO_PI = new BigDecimal(2 * Math.PI);
    /**
     * Calculates sin(D)
     * @param D
     * @return
     */
    public static BigDecimal bigSin(BigDecimal D) {
        if(D.abs().compareTo(TWO_PI) <= 0){
            return new BigDecimal(Math.sin(Double.parseDouble(D.toString())));
        }
        final int scale = 20;
        BigDecimal f = D.divide(TWO_PI, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = f.subtract(new BigDecimal(F)).multiply(TWO_PI);
        float ff = f.floatValue();
        return new BigDecimal(Math.sin(ff));
    }

    /**
     * Calculates cos(D)
     * @param D
     * @return
     */
    public static BigDecimal bigCos(BigDecimal D) {
        if(D.abs().compareTo(TWO_PI) <= 0){
            return new BigDecimal(Math.cos(Double.parseDouble(D.toString())));
        }
        final int scale = 20;
        BigDecimal f = D.divide(TWO_PI, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = f.subtract(new BigDecimal(F)).multiply(TWO_PI);
        float ff = f.floatValue();
        return new BigDecimal(Math.cos(ff));

    }

    /**
     * Calculates tan(D)
     * @param D
     * @return
     */
    public static BigDecimal bigTan(BigDecimal D) {
        BigDecimal sin = bigSin(D);
        BigDecimal cos = bigCos(D);
        BigDecimal tan = null;
        try {
            tan = sin.divide(cos, 20, BigDecimal.ROUND_HALF_DOWN);
            return tan;
        } catch (ArithmeticException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

    /**
     * Calculates sinh(D)
     * @param D
     * @return
     */
    public static BigDecimal bigSinh(BigDecimal D) {
        try {
            BigDecimal expD = Exp.exp(D);
            BigDecimal inverse = BigDecimal.ONE.divide(expD, 20, BigDecimal.ROUND_HALF_DOWN);

            BigDecimal result = (expD.subtract(inverse).divide(new BigDecimal("2"), 20, BigDecimal.ROUND_HALF_DOWN));
            return result;
        } catch (ArithmeticException e) {
            throw e;
        }
    }

    /**
     * Calculates cosh(D)
     * @param D
     * @return
     */
    public static BigDecimal bigCosh(BigDecimal D) {
        try {
            BigDecimal expD = Exp.exp(D);
            BigDecimal inverse = BigDecimal.ONE.divide(expD, 20, BigDecimal.ROUND_HALF_DOWN);

            BigDecimal result = (expD.add(inverse).divide(new BigDecimal("2"), 20, BigDecimal.ROUND_HALF_DOWN));
            return result;
        } catch (ArithmeticException e) {
            throw e;
        }
    }

    /**
     * Calculates tanh(D)
     * @param D
     * @return
     */
    public static BigDecimal BigTanh(BigDecimal D) {
        try {
            return bigSinh(D).divide(bigCosh(D), 20, BigDecimal.ROUND_HALF_DOWN);
        } catch (ArithmeticException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
