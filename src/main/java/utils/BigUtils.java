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
