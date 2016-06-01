package factoring;

import java.math.BigInteger;
import java.util.Random;

import arithmetic.JacobiSymbol;

public class WilliamsPPO {

	private static final Random random = new Random(System.currentTimeMillis());
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");
	private static final BigInteger FIVE = new BigInteger("5");


	/**
	 * Factors the BigInteger N, using the Williams P+1 algorithm. If failure
	 * after factorization attempts, the algorithm will fallback to rho
	 * factorization.
	 * 
	 * @param N number to be factored
	 * @return prime factor of N
	 */
	public static BigInteger ppoFactor(BigInteger N) {
		if (N.isProbablePrime(99))
			return N;
		int attempts = 2;
		while (attempts++ < 1000) {
			BigInteger m = new BigInteger(String.valueOf(attempts));
			BigInteger count = TWO;
			int trials = 2;
			while (trials++ < 100) {
				BigInteger r = calcNext(N, m, count);
				r = r.subtract(TWO).gcd(N);
				if (BigInteger.ONE.compareTo(r) < 0 && N.compareTo(r) > 0) {
					if (r.isProbablePrime(99)) {
						System.out.println("found factor   " + r.toString());
						return r;
					} else
						return ppoFactor(r);
				}
				// factorialize
				count = count.multiply(new BigInteger(String.valueOf(trials)));
			}

			if (N.isProbablePrime(99))
				return N;

		}
		System.out.println("Factorization failed (PPO): revert to RHO");
		return PollardRho.pollardRho(N);

	}

	/**
	 * Calculates the next number in the lucas sequence used for finding
	 * potential factors of N. base is the first number in the sequence and m is
	 * the mth, to be calculated.
	 * 
	 * @param N
	 * @param base
	 * @param m
	 * @return mth value in lucas sequence.
	 */
	public static BigInteger calcNext(BigInteger N, BigInteger base,
			BigInteger m) {
		BigInteger x = base;
		BigInteger y = base.multiply(base).subtract(TWO).mod(N);
		byte[] bx = m.toByteArray();
		int msbi = 0;
		byte[] b;
		if ((bx[0] & 0xFF) == 0) {
			byte[] b2 = new byte[bx.length - 1];
			for (int i = 1; i < bx.length; i++) {
				b2[i - 1] = bx[i];
			}
			b = b2;
		} else {
			b = bx;
		}
		for (byte bbbb : b) {
			// System.out.println("be "+Integer.toHexString(bbbb));
		}
		for (int i = 0; i < b.length; i++) {
			// first byte
			if (i == 0) {
				int j = 8;
				while (j-- >= 0) {
					if (((b[0] >> j) & 1) == 1) {
						msbi = j; // System.out.println("msbi is "+j);
						break;
					}
				}
				if (msbi > 0) {
					for (int k = msbi - 1; k >= 0; k--) {
						if (((b[0] >> k) & 1) == 1) {
							x = x.multiply(y).subtract(base).mod(N);
							y = y.multiply(y).subtract(TWO).mod(N);
						} else {
							y = x.multiply(y).subtract(base).mod(N);
							x = x.multiply(x).subtract(TWO).mod(N);
						}
					}
				}
			} else {
				for (int k = 7; k >= 0; k--) {
					if (((b[i] >> k) & 1) == 1) {
						x = x.multiply(y).subtract(base).mod(N);
						y = y.multiply(y).subtract(TWO).mod(N);
					} else {
						y = x.multiply(y).subtract(base).mod(N);
						x = x.multiply(x).subtract(TWO).mod(N);
					}
				}
			}
		}
		return x;

	}

}
