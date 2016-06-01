package factoring;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import utils.RootFinder;

public class PollardPMO {

	private static final Random random = new Random(System.currentTimeMillis());
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FIVE = new BigInteger("5");
    
	/**
	 * Product of first 5 primes.
	 */
	public static final BigInteger P5 = new BigInteger("120");
	/**
	 * Product of first 10 primes.
	 */
	public static final BigInteger P10 = new BigInteger("3628800");
	/**
	 * Product of first 15 primes.
	 */
	public static final BigInteger P15 = new BigInteger("1307674368000");
	/**
	 * Product of first 20 primes.
	 */
	public static final BigInteger P20 = new BigInteger("2432902008176640000");
	/**
	 * Product of first 25 primes.
	 */
	public static final BigInteger P25 = new BigInteger("15511210043330983907819520");
	/**
	 * Product of first 35 primes.
	 */
	public static final BigInteger P35 = new BigInteger("10333147966386144222209170348167175077888");
	
	/**
     * Factors the BigInteger N by Pollard P-1 algorithm.
     * 
     * @see <i> An Introduction to Mathematical Cryptography pp 133 - 135</i>
     * @param N Integer to be factored.
     * @return A prime factor of N.
     */
    public static BigInteger pmoFactor(BigInteger N) {

		// do obvious tests
		if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0)
			return TWO;

		if (N.mod(THREE).compareTo(BigInteger.ZERO) == 0)
			return THREE;

		if (N.mod(FIVE).compareTo(BigInteger.ZERO) == 0)
			return FIVE;

		// find a reasonable bound.
		BigInteger bound = RootFinder.nRoot(new BigDecimal(N), 2, 10).toBigInteger();
		if(bound.compareTo(P35) > 0)
			bound = P35;
		else if(bound.compareTo(P25) > 0)
			bound = P25;
		else if(bound.compareTo(P20) > 0){
			bound = P20;
		}
		else if(bound.compareTo(P15) > 0){
			bound = P15;
		}
		else if(bound.compareTo(P10) > 0){
			bound = P10;
		}
		else if(bound.compareTo(P5) > 0){
			bound = P5;
		}
		
		// maximum 100000 tests.
		int cnt = 1000000;
		while (cnt-- > 0) {
			BigInteger a = new BigInteger(N.bitLength(), random);

			BigInteger g = a.modPow(bound, N);
			g = g.subtract(BigInteger.ONE).mod(N);
			BigInteger f = g.gcd(N);
			if (BigInteger.ONE.compareTo(f) < 0 && N.compareTo(f) > 0) {
				if (f.isProbablePrime(99))
					return f;
				else
					return pmoFactor(f);
			}
		}
		if (N.isProbablePrime(99)) {
			return N;
		} else {
			// Failed to factor N, so try another technique, i.e. Pollard rho.
			try {
				return PollardRho.pollardRho(N);
			} catch (Throwable t) {
				System.out.println("Factorization failed.");
				System.exit(0);
				return null;
			}
		}
	}
}
