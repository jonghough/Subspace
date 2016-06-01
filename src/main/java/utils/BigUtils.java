package utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigUtils {

    public static BigInteger inc(BigInteger n) {
        return n.add(BigInteger.ONE);
    }

    public static BigInteger dec(BigInteger n) {
        return n.subtract(BigInteger.ONE);
    }

    public static BigDecimal BigSin(BigDecimal D) {
        BigDecimal pi2 = BigDecimal.valueOf(2 * Math.PI);
        final int scale = 20;
        BigDecimal f = D.divide(pi2, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = new BigDecimal(F).subtract(f).multiply(pi2);
        float ff = f.floatValue();
        return new BigDecimal(Math.sin(ff));
    }

    public static BigDecimal BigCos(BigDecimal D) {
        BigDecimal pi2 = BigDecimal.valueOf(2 * Math.PI);
        final int scale = 20;
        BigDecimal f = D.divide(pi2, scale, BigDecimal.ROUND_DOWN);
        BigInteger F = f.toBigInteger();
        f = new BigDecimal(F).subtract(f).multiply(pi2);
        float ff = f.floatValue();
        return new BigDecimal(Math.cos(ff));

    }

    public static BigDecimal BigTan(BigDecimal D) {
        BigDecimal sin = BigSin(D);
        BigDecimal cos = BigCos(D);
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

    public static BigDecimal BigSinh(BigDecimal D) {
        try {
            BigDecimal expD = Exp.exp(D);
            BigDecimal inverse = BigDecimal.ONE.divide(expD, 20, BigDecimal.ROUND_HALF_DOWN);

            BigDecimal result = (expD.subtract(inverse).divide(new BigDecimal("2"), 20, BigDecimal.ROUND_HALF_DOWN));
            return result;
        } catch (ArithmeticException e) {
            throw e;
        }
    }

    public static BigDecimal BigCosh(BigDecimal D) {
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
            return BigSinh(D).divide(BigCosh(D), 20, BigDecimal.ROUND_HALF_DOWN);
        } catch (ArithmeticException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
