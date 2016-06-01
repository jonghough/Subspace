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
}
