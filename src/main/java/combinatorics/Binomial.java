package combinatorics;


import java.math.BigInteger;

public class Binomial {


    public static long coefficient(int n, int k){

        if(n < 0 || k < 0)
            return 0;
        if(k > n)
            throw new IllegalArgumentException("second argument cannot be larger than the first argument");

        if(k == 0 || k == n)
            return 1;

        else if(k == 1 || k == n - 1)
            return n;

        if(k > n - k) k = n - k;

        long coeff = 1L;
        for (int i = 1; i <= k; i++) {
            coeff *= (n - (k - i));
            if (coeff < 0) throw new RuntimeException("Long integer overflow! "+n+", "+k+",   "+i+"  /  "+coeff);
            coeff /= i;
        }
        return coeff;
    }

    public static BigInteger coefficient(BigInteger n, BigInteger k) {

        if (n.compareTo(BigInteger.ZERO) < 0 || k.compareTo(BigInteger.ZERO) < 0)
            return BigInteger.ZERO;
        if (k.compareTo(n) > 0)
            throw new IllegalArgumentException("second argument cannot be larger than the first argument");

        if (k.compareTo(BigInteger.ZERO) == 0 || k.compareTo(n) == 0)
            return BigInteger.ONE;

        else if (k.compareTo(BigInteger.ONE) == 0 || k.compareTo(n.subtract(BigInteger.ONE)) == 0)
            return n;

        if (k.compareTo(n.subtract(k)) > 0) k = n.subtract(k);

        BigInteger coeff = BigInteger.ONE;
        for (int i = 1; i <= k.intValue(); i++) {
            coeff = coeff.multiply(n.subtract(k.subtract(new BigInteger(String.valueOf(i)))));
            coeff = coeff.divide(new BigInteger(String.valueOf(i)));
        }
        return coeff;
    }
}
