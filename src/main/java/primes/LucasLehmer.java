
package primes;

import java.math.BigInteger;

/**
 * Contains functions for primality test using Lucas-Lehmer algorithm.
 * 
 * @author Jon Hough
 */
public class LucasLehmer {


    /**
     * Constant 2!
     */
    private static final BigInteger TWO = new BigInteger("2");
    /**
     * Calculates whether the integer 2^pExponent - 1 is a Mersenne prime using
     * Lucas-Lehmer test.
     * 
     * @param pExponent prime Exponent to test.
     * @return true if mersenne prime, false otherwise.
     */
    public static boolean isPrime(int pExponent) {
        if (pExponent <= 1)
            throw new IllegalArgumentException("Exponent must be greater than one.");

        // first test pExponent is prime
        if (!Primality.isPrime(pExponent))
            throw new IllegalArgumentException(
                    "To test for Mersenne primes, the exponent must be prime.");
        BigInteger test = (TWO.pow(pExponent)).subtract(BigInteger.ONE);
        BigInteger s = new BigInteger("4");
        int count = pExponent - 2;
        while (count-- > 0) {
            s = (s.multiply(s.multiply(s).subtract(TWO))).mod(test);
        }
        if (s.compareTo(BigInteger.ZERO) == 0)
            return true;
        else
            return false;
    }
}
