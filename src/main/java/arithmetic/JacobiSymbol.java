package arithmetic;

import java.math.BigInteger;

/**
 * Class for calculating Jacobi identities of integer pairs.
 * 
 */
public class JacobiSymbol {

	private static BigInteger ZERO = BigInteger.ZERO;
	private static BigInteger MINUS_ONE = new BigInteger("-1");
	private static BigInteger ONE = BigInteger.ONE;
	private static BigInteger TWO = new BigInteger("2");
	private static BigInteger THREE = new BigInteger("3");
	private static BigInteger FOUR = new BigInteger("4");
	private static BigInteger FIVE = new BigInteger("5");
	private static BigInteger EIGHT = new BigInteger("8");

	/**
	 * Calculates the Jacobi Symbol (a/b) where a and b are integers
	 * (represented by Java BigIntegers).<br>
	 * <b>Rules</b><br>
	 * If a and b are not coprime then (a/b) = 0;
	 * If b is zero or even then the Jacobi Symbol is 0;<br>
	 * If b = 1 mod 4 and a < 0 then (a/b) = (-a/b); <br>
	 * If b = 3 mod 4 and a < 0 then (a/b) = -(a/b); <br>
	 * <br>
	 * <b>Quadratic Reciprocity Rules for odd positive integers p,q</b><br>
	 * (-1/p) = 1 if p = 1 mod 4, else -1.<br>
	 * (2/p) =1 if p = 1,7 mod 8, -1 if p = 3,5 mod 8;<br>
	 * (p/q) = (q/p) if p,q = 1 mod 4, -(q/p) if p,q = 3 mod 4;<br>
	 * <i>An Introduction to Mathematical Cryptography pp.168-172.</i>
	 * 
	 * @param numerator
	 * @param denominator
	 * @return The jacobi symbol (numerator/denominator)
	 */
	public static int calculateJacobi(BigInteger numerator,
			BigInteger denominator) {
		int jacobi = 1;
		if (numerator.compareTo(ZERO) == 0)
			return 0;
		
		
		if (numerator.compareTo(ZERO) < 0) {
			numerator = numerator.multiply(MINUS_ONE);
			if (denominator.mod(FOUR).compareTo(THREE) == 0)
				jacobi = -1;
		}
		
		if(numerator.gcd(denominator).compareTo(ONE) != 0){
			return 0;
		}

		while (numerator.compareTo(ZERO) != 0) {
			if (numerator.compareTo(ONE) == 0)
				return jacobi;
			
			// while numerator is even
			while (numerator.mod(TWO).compareTo(ZERO) == 0) {
				numerator = numerator.divide(TWO);

				if (denominator.mod(EIGHT).compareTo(THREE) == 0
						|| denominator.mod(EIGHT).compareTo(FIVE) == 0) {
					jacobi *= -1;
				}
			}

			// Use quadratic reciprocity
			if (numerator.mod(FOUR).compareTo(THREE) == 0
					&& denominator.mod(FOUR).compareTo(THREE) == 0) {
				jacobi *= -1;
			}

			BigInteger temp = denominator.mod(numerator);
			denominator = numerator;
			numerator = temp;
		}

		return jacobi;
	}

	/**
	 * Jacobi symbol for long type.
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static int calculateJacobi(long numerator, long denominator) {
		int jacobi = 1;
		if (numerator == 0)
			return 0;

		if (numerator < 0) {
			numerator = numerator * -1;
			if (denominator % 4 == 3)
				jacobi *= -1;
		}

		if(gcd(numerator, denominator) != 1){
			return 0;
		}

		while (numerator != 0) {

			if (numerator == 1)
				return jacobi;
			// while numerator is even
			while (numerator % 2 == 0) {
				numerator /= 2;

				if (denominator % 8 == 3 || denominator % 8 == 5) {
					jacobi *= -1;
				}
			}

			// Use quadratic reciprocity
			if (numerator % 4 == 3 && denominator % 4 == 3) {
				jacobi *= -1;
			}
			long temp = denominator % numerator;
			denominator = numerator;
			numerator = temp;

		}

		return jacobi;
	}

	private static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
