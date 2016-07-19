package primes;

import java.math.BigInteger;

import utils.RootFinder;

/**
 * Naive Primality Test class. Contains function for performing naive test of
 * primality. i.e. test division by all integers less than root(n).
 *
 *
 */
public class Primality {
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");

	/**
	 * Preforms Naive trial division test of primality on val.
	 *
	 * @param val
	 * @return
	 */
	public static boolean isPrime(long val) {
		if (val <= 1)
			return false;
		if (val == 2)
			return true;

		if (val % 2 == 0)
			return false;

		for (int i = 3; i < Math.sqrt(val) + 1; i += 2) {
			if (val % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Naive isPrime test for big integers.
	 * @param val
	 * @return
	 */
	public static boolean isPrime(BigInteger val) {
		if (val.compareTo(BigInteger.ONE) <= 0)
			return false;;
		if (val.compareTo(TWO) == 0)
			return true;

		if (val.mod(TWO).equals(BigInteger.ZERO))
			return false;
		BigInteger i = THREE;
		BigInteger sqrt = RootFinder.getRootCeiling(val).add(BigInteger.ONE);
		while (i.compareTo(sqrt) < 0) {
			if (val.mod(i).compareTo(BigInteger.ZERO)==0){
				return false;
			}
			i = i.add(TWO);
		}

		return true;
	}
}
