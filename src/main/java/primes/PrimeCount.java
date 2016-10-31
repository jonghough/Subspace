package primes;

import java.math.BigDecimal;
import java.math.BigInteger;

import utils.BigUtils;
import utils.RootFinder;
import utils.Settings;

/**
 * Functions for counting primes.
 *
 *
 */
public class PrimeCount {

	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigDecimal THREE_DECIMAL = new BigDecimal("3");
	private static final BigDecimal HALF_DECIMAL = new BigDecimal("0.5");

	/**
	 * Returns the Nth prime, starting with first prime = 2. Currently uses
	 * naive primality test.
	 *
	 * @param n
	 * @return
	 */
	public static int calcNthPrime(int n) {

		if (n <= 0)
			throw new IllegalArgumentException("Argument must be positive.");
		if (n == 1)
			return 2;
		int prime = 2;

		int i = 0, count = 0;
		i = 1;
		count = 3; // start at 3

		while (i < n) {
			if (Primality.isPrime(count)) {
				prime = count;
				i++;
			}
			count++;
		}
		return prime;
	}

	public static long calcNthPrime(long n) {
		if (n <= 0)
			throw new IllegalArgumentException("Argument must be positive.");
		if (n == 1)
			return 2;
		long prime = 2;
		long i = 1;

		long count = 3; // start at 3
		while (i < n) {
			if (Primality.isPrime(count)) {
				prime = count;
				i++;
			}
			count++;
		}
		return prime;
	}

	/**
	 * Calculates the Nth prime where N is the big integer argument and 2 is the
	 * first prime. Even though argument is BigInteger, it takes far too long to
	 * attempt this algorithm for values outside int range.
	 *
	 * @param n
	 * @return
	 */
	public static BigInteger calcNthPrime(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) <= 0)
			throw new IllegalArgumentException("Argument must be positive.");
		if (n.equals(BigInteger.ONE))
			return TWO;
		BigInteger prime = TWO;

		BigInteger i = null, count = null;
		i = BigInteger.ONE;
		count = THREE; // start at 3

		while (i.compareTo(n) < 0) {
			if (count.isProbablePrime(100)) {
				prime = count;
				i = i.add(BigInteger.ONE);
			}
			count = count.add(BigInteger.ONE);

		}
		return prime;

	}

	public static int nextPrime(int currentPrime) {
		int p = currentPrime + 1;
		while (true) {
			if (Primality.isPrime(p))
				return p;
			else
				p++;
		}

	}

	public static BigInteger calcPreviousPrime(BigInteger currentPrime) {
		if (currentPrime.compareTo(TWO) <= 0)
			throw new IllegalArgumentException("Argument must be positive.");

		BigInteger prime = TWO;
		BigInteger i = currentPrime;

		while (i.compareTo(BigInteger.ONE) > 0) {
			i = BigUtils.dec(i);
			if (Primality.isPrime(i)) {
				prime = i;
				break;
			}
		}
		return prime;
	}

	public static BigInteger calcNextPrime(BigInteger currentPrime) {
		if (currentPrime.compareTo(BigInteger.ONE) <= 0)
			throw new IllegalArgumentException("Argument must be positive.");
		if (currentPrime.equals(TWO))
			return new BigInteger("3");
		BigInteger prime = TWO;
		BigInteger i = currentPrime;

		while (i.compareTo(BigInteger.ONE) > 0) {
			i = BigUtils.inc(i);
			if (Primality.isPrime(i)) {
				prime = i;
				break;
			}
		}
		return prime;
	}

	/**
	 * Calculates the number of positive integers that are less than m and are
	 * not divisible by any of the first n primes. Uses recursive relation <br>
	 * <b>phi(m,n) = phi(m,n-1) - phi(floor(m/p_n),n-1)</b>
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	public static int phi(float m, int n) {
		if (m < 0 || n < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n == 0) {
			return (int) m;

		} else if (m < 0.001f)
			return 0;
		else {
			return phi(m, n - 1)
					- phi((int) Math.floor((m / (float) (calcNthPrime(n)))),
							n - 1);
		}
	}

	public static long phi(float m, long n) {
		if (m < 0 || n < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n == 0) {
			return (long) m;

		} else if (m < 0.001f)
			return 0;
		else {
			return phi(m, n - 1)
					- phi((long) Math.floor((m / (float) (calcNthPrime(n)))),
							n - 1);
		}
	}

	/**
	 * Phi for BigIntegers and BigDecimals
	 *
	 * @param m
	 * @param n
	 * @return
	 */
	public static BigInteger phi(BigDecimal m, BigInteger n) {
		if (m.compareTo(BigDecimal.ZERO) < 0
				|| n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n.compareTo(BigInteger.ZERO) == 0) {
			return m.toBigInteger();
		} else {
			BigDecimal np = new BigDecimal(calcNthPrime(n));
			return phi(m, BigUtils.dec(n)).subtract(
					phi(m.divide(np, 6, Settings.ROUNDING), BigUtils.dec(n)));
		}
	}

	public static BigInteger phi2(BigInteger m, BigInteger n) {
		if (m.compareTo(BigInteger.ZERO) < 0
				|| n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n.compareTo(BigInteger.ZERO) == 0) {
			return m;
		} else {
			BigDecimal np = new BigDecimal(calcNthPrime(n));
			return phi2(m, BigUtils.dec(n)).subtract(
					phi2(new BigDecimal(m).divide(np, 6, Settings.ROUNDING)
							.toBigInteger(), BigUtils.dec(n)));
		}
	}

	public static BigInteger phi2(BigInteger m, BigInteger n,
			BigInteger nextPrime) {
		if (m.compareTo(BigInteger.ZERO) < 0
				|| n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n.compareTo(BigInteger.ZERO) == 0) {
			return m;
		} else {
			if (nextPrime.compareTo(TWO) == 0)
				return phi(new BigDecimal(m), n);
			else {
				BigDecimal np = new BigDecimal(calcPreviousPrime(nextPrime));
				return phi2(m, BigUtils.dec(n), np.toBigInteger()).subtract(
						phi2(new BigDecimal(m)
								.divide(new BigDecimal(nextPrime), 6,
										Settings.ROUNDING).toBigInteger(),
								BigUtils.dec(n), np.toBigInteger()));
			}
		}
	}

	/**
	 * Calculates phi using previously calculated nth prime, nextPrime. Faster,
	 * since the calculation to find (n-1)th prime just needs to look down from
	 * nextPrime, and not count up from 2.
	 *
	 * @param m
	 * @param n
	 * @param nextPrime
	 * @return
	 */
	public static BigInteger phi(BigDecimal m, BigInteger n,
			BigInteger nextPrime) {
		if (m.compareTo(BigDecimal.ZERO) < 0
				|| n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Arguments must be non-negative");
		if (n.compareTo(BigInteger.ZERO) == 0) {
			return m.toBigInteger();
		} else if (m.compareTo(new BigDecimal("0")) <= 0)
			return BigInteger.ZERO;
		else {
			if (nextPrime.compareTo(TWO) == 0)
				return phi(m, n);
			else {
				BigInteger np = calcPreviousPrime(nextPrime);
				return phi(m, BigUtils.dec(n), np)
						.subtract(
								phi(m.divide(new BigDecimal(nextPrime), 6,
										BigDecimal.ROUND_HALF_UP), BigUtils
										.dec(n), np));
			}
		}
	}



	/**
	 * Calculates the number of primes no exceeding the real number (as a float)
	 * m. Starting at 2 as the first prime. Essentially an implementation of
	 * <b>Meissel formula</b>.<br>
	 *
	 * @param m
	 * @return
	 */
	public static int pi(float m) {
		if (m < 2)
			return 0;
		if (m < 3)
			return 1;
		if (m < 5)
			return 2;
		if (m < 7)
			return 3;
		if (m < 11)
			return 4;

		int cbrt = pi((float) Math.cbrt((double) m));

		int mu = pi((float) Math.sqrt((double) m)) - cbrt;
		return phi(m, cbrt) + (int)(cbrt * (mu + 1) + mu *( mu - 1) * 0.5f)
				- 1 - sumPi(m, cbrt, mu);
	}

	public static long piLong(float m) {
		if (m < 2)
			return 0;
		if (m < 3)
			return 1;
		if (m < 5)
			return 2;
		if (m < 7)
			return 3;
		if (m < 11)
			return 4;
		long cbrt = pi((float) Math.cbrt((double) m));

		long mu = pi((float) Math.sqrt((double) m)) - cbrt;
		return  phi(m, cbrt) + cbrt * (mu + 1) + (long)((mu * mu - mu) * 0.5f)
				- 1 - sumPi(m, cbrt, mu);
	}

	/**
	 *
	 * @param m
	 * @return
	 */
	public static BigInteger pi(BigDecimal m) {
		if (m.compareTo(BigDecimal.ONE) <= 0)
			return BigInteger.ZERO;
		if (m.compareTo(THREE_DECIMAL) < 0) {
			return BigInteger.ONE;
		}
		final int scale = 100;
		BigInteger cbrt = pi(RootFinder.cubeRoot(m, 100));
		BigInteger mu = pi(RootFinder.squareRoot(m, 100)).subtract(cbrt);

		return (phi(m, cbrt).add(cbrt
				.multiply(mu.add(BigInteger.ONE))
				.add((new BigDecimal(mu.multiply(mu).subtract(mu))
						.multiply(HALF_DECIMAL)).toBigInteger())
				.subtract(BigInteger.ONE).subtract(sumPi(m, cbrt, mu))));

	}

	/**
	 * Sums pi(m/x) where x starts at n+1 up to mu. Used for the calulcation of
	 * pi(N) using Meissel's method.
	 *
	 * @param m
	 * @param n
	 * @param mu
	 * @return
	 */
	private static int sumPi(float m, int n, int mu) {
		int i = 1;
		int total = 0;
		int p = calcNthPrime(n);
		while (i <= mu) {
			i++;
			p = nextPrime(p);
			total += pi(m / p);
		}
		return total;
	}

	private static long sumPi(float m, long n, long mu) {
		long i = 1;
		long total = 0;
		while (i <= mu) {
			total += piLong(m / calcNthPrime(n + i));
			i++;
		}
		return total;
	}



	private static BigInteger sumPi(BigDecimal m, BigInteger n, BigInteger mu) {
		BigDecimal tmp;// = BigDecimal.ZERO;
		BigInteger i = BigInteger.ONE;
		BigInteger total = BigInteger.ZERO;

		BigInteger p = calcNthPrime(n);
		while (i.compareTo(mu) <= 0) {
			p = calcNextPrime(p);
			tmp = new BigDecimal(p);
			total = total.add(pi(m.divide(tmp, 6, BigDecimal.ROUND_HALF_UP)));
			i = i.add(BigInteger.ONE);

		}
		return total;
	}

}
