package primes;

import java.util.ArrayList;

public class Sieve {

	/**
	 * Implementation of Sieve of Eratosthenes for ints. Will return a list of
	 * all primes less than N, where N > 1.
	 * 
	 * @param N
	 * @return
	 */
	public static ArrayList<Integer> eratosthenes(Integer N) {
		if (N <= 1)
			throw new IllegalArgumentException(
					"Integer must be greater than one.");
		
		ArrayList<Boolean> primes = new ArrayList<Boolean>(N);
		// integer
		final int sqrt = (int) Math.sqrt((double) N) + 1;
		for (int i = 0; i < N; i++) {
			primes.add(true);
		}

		for (int i = 2; i < sqrt; i++) {
			int j = 0;
			while (i * i + i * j < N) {
				primes.set(i * i + i * j, false);
				j++;
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 2; i < N; i++) {
			if (primes.get(i) == true)
				result.add(i);
		}
		return result;
	}

	
	/**
	 * Implementation of Atkin Sieve for returning a list of primes less than
	 * integer N.
	 * @param N
	 * @return
	 */
	public static ArrayList<Integer> atkinSieve(int N) {
		if(N <= 1){ 
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(0);
			return l;
		}
		final int sqrt = (int) Math.sqrt((double) N) + 1;
		ArrayList<Boolean> primes = new ArrayList<Boolean>(N);
		for (int i = 0; i < N; i++) {
			primes.add(false);
		}

		for (int i = 0; i < sqrt; i++) {
			for (int j = 0; j < sqrt; j++) {
				int n = 4 * i * i + j * j;// 4i^2 +j^2
				if (n < N && (n % 12 == 1 || n % 12 == 5)) {
					primes.set(n, !primes.get(n));
				}
				n = 3 * i * i + j * j;// 3i^2 +j^2
				if (n < N && n % 12 == 7) {
					primes.set(n, !primes.get(n));
				}
				n = 3 * i * i - j * j; // 3i^2 - j^2
				if (n < N && i > j && n % 12 == 11) {
					primes.set(n, !primes.get(n));
				}
			}
		}

		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(2);
		result.add(3);
		for (int i = 2; i < N; i++) {
			long I = (long)i;
			int j = 0;
			if (primes.get(i) == true) {
				long J = (long)j;
				while (I*I + I*J < Integer.MAX_VALUE && i * i + i * j < N) {
					primes.set(i * i + i * j, false);
					j++;
				}
				result.add(i);
			}
		}
		return result;
	}

}
