package transcendance;


import java.math.BigDecimal;
import java.math.BigInteger;

public class BigTrigonometry {

    public static BigDecimal bigSin(BigDecimal D) {
        BigDecimal pi2 = BigDecimal.valueOf(2 * Math.PI);
        final int scale = 20;
        BigDecimal f = D.divide(pi2, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = new BigDecimal(F).subtract(f).multiply(pi2);
        float ff = f.floatValue();
        return new BigDecimal(Math.sin(ff));
    }

    public static BigDecimal bigCos(BigDecimal D) {
        BigDecimal pi2 = BigDecimal.valueOf(2 * Math.PI);
        final int scale = 20;
        BigDecimal f = D.divide(pi2, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = new BigDecimal(F).subtract(f).multiply(pi2);
        float ff = f.floatValue();
        return new BigDecimal(Math.cos(ff));

    }

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
