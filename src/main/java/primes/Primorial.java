package primes;


import java.math.BigInteger;
import java.util.ArrayList;

public class Primorial {

    /**
     * Calculates the <i>nth primorial</i>. Which is the multiplication
     * of the first <i>n</i> prime numbers, where prime index begins at 1.
     * (i.e prime number 1 is 2).
     * <br>
     * Will throw a <code>IllegalArgumentException</code> if the argument is
     * non-positive.
     * @param n positive integer indicating the nth prime.
     * @return primorial.
     */
    public static BigInteger primorial(int n){
        if(n <= 0) throw new IllegalArgumentException("Argument must be positive.");
        else if(n == 1) return new BigInteger("2");
        else{
            int nthPrime = PrimeCount.calcNthPrime(n);
            //sieve to find all primes...
            ArrayList<Integer> primes = Sieve.eratosthenes(nthPrime + 1);
            return primes.stream().map(i -> new BigInteger(i.toString())).reduce((i,j) ->
                    i.multiply(j)).get();
        }


    }
}
