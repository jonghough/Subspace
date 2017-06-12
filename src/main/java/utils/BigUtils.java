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

    /**
     * Calculates the inverse of <i>k</i> modulo <i>n</i>, using the
     * <i>extends euclidean algorithm</i>, if such an inverse exists.
     * If an inverse doesn't exist (e.g. not coprime k,n) then an
     * <code>ArithmeticException</code> will be thrown.
     * @param k integer value to find inverse of.
     * @param n integer value
     * @return invers eof k modulo n.
     */
    public static int modInverse(int k, int n){
        int t = 0;
        int nt = 1;
        int r = n;
        int nr = k;

        while(nr != 0){
            int q = r / nr;
            int tmp = nt;
            nt = t - q * nt;
            t = tmp;

            tmp = nr;
            nr = r - q * nr;
            r = tmp;
        }

        if(r > 1) throw new ArithmeticException("Not invertible");
        if(t < 0) t += n;
        return t;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, (b + a) % b);
    }

}
