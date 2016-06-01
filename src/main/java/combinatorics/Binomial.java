package combinatorics;


public class Binomial {


    public static long coefficient(int n, int k){
        if(n < 0 || k < 0)
            throw new IllegalArgumentException("Arguments must be non-negative");
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
            if (coeff < 0) throw new RuntimeException("Long integer overflow!");
            coeff /= i;
        }
        return coeff;
    }
}
