
package primes;

import java.math.BigInteger;

import factoring.Factorizor;

/**
 * Liouville's Function and related functions.
 * 
 * @author Jon Hough
 *
 */
public class Liouville {

    /**
     * Liouville's function. Defined as -1 to the power of the
     * number of prime factors of N, counting multiples.
     * @param N
     * @return Liouville's function(N)
     */
    public static int l(BigInteger N) {
        int primeFactors = Factorizor.factor(N, Factorizor.FactorMethod.RHO).size();
        return new BigInteger("-1").pow(primeFactors).intValue();
    }
    
    
    /**
     * Computes the sum of l(n) for all n from 1 to N.
     * @param N
     * @return
     */
    public static int L(BigInteger N){
        if(N.compareTo(BigInteger.ZERO) <= 0) throw new IllegalArgumentException("Cannot have non-positive argument.");
        int sum = 0;
        BigInteger counter = BigInteger.ONE;
        while(counter.compareTo(N)<= 0){
            counter = counter.add(BigInteger.ONE);
            sum += l(counter);
        }
        return sum;
    }

}
