package arithmetic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import factoring.Factorizor;
import utils.HashMapBuilder;

/**
 * Euler Totient function calculator.
 * 
 *
 *
 */
public class Totient {

	/**
	 * Returns totient function fo BigInteger n. Uses Pollard Rho algorithm to
	 * factor n.<br>
	 * 
	 * @param n
	 *            BigInteger to factor.
	 * @return
	 */
	public static BigInteger totient(BigInteger n) {
		if (n.compareTo(BigInteger.ONE) <= 0) {
			return BigInteger.ONE;
		}
		ArrayList<BigInteger> factorList = Factorizor.factor(n, Factorizor.FactorMethod.RHO);
		HashMap<BigInteger, Integer> map = HashMapBuilder.toHashMap(factorList);
		// final product to calculate totient.
		BigInteger prod = BigInteger.ONE;
		Iterator<Entry<BigInteger, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<BigInteger, Integer> entry = iter.next();
			BigInteger primeVal = (entry.getKey().pow(entry.getValue()))
					.subtract(entry.getKey().pow(entry.getValue() - 1));
			prod = prod.multiply(primeVal);

		}

		return prod;
	}
}
