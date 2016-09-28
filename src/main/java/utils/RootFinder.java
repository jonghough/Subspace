package utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Static functions for calculating roots and related functions for
 * BigIntegers and BigDecimals.
 * @author Jon Hough
 */
public class RootFinder {

	private static BigDecimal ZERO = new BigDecimal("0");
	private static BigDecimal EPSILON = new BigDecimal("0.000001");
	private static BigDecimal TWO = new BigDecimal("2");

	/**
	 * Calculates an approximation for the square root of BigDecimal value n.
	 * 
	 * @param n
	 *            the value to root.
	 * @param scale
	 * @return the root of n.
	 * @throws IllegalArgumentException
	 *             if n is negative or zero.
	 */
	public static BigDecimal squareRoot(BigDecimal n, int scale)
			throws IllegalArgumentException {

		if (n.compareTo(ZERO) <= 0) {
			throw new IllegalArgumentException(
					"Only positive integers accepted.");
		}
		BigDecimal temp = BigDecimal.ONE;
		BigDecimal root = (n.add(n.divide(temp, scale, Settings.ROUNDING)))
				.divide(TWO, scale, BigDecimal.ROUND_HALF_UP);
		while (root.subtract(temp).abs().compareTo(EPSILON) > 0) {
			temp = new BigDecimal(root.toString());
			root = (temp.add(n.divide(temp, scale, Settings.ROUNDING)))
					.divide(TWO, scale, Settings.ROUNDING);
		}

		return root;
	}

	/**
	 * Calculates the approximate (round up) integer square root of integer n.
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger getApproxRoot(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Cannot use negative integers.");
		BigDecimal p = RootFinder.squareRoot(new BigDecimal(n
				.toString()), 10);
		BigInteger roundUp = p.setScale(0, Settings.ROUNDING)
				.toBigIntegerExact();

		return roundUp;
	}

	/**
	 * Floor of square root.
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger getRootFloor(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Cannot use negative integers.");
		BigDecimal p = RootFinder.squareRoot(new BigDecimal(n
				.toString()), 10);
		BigInteger rounddown = p.setScale(0, BigDecimal.ROUND_FLOOR)
				.toBigIntegerExact();

		return rounddown;
	}

	/**
	 * Ceiling of square root.
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger getRootCeiling(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0)
			throw new IllegalArgumentException("Cannot use negative integers.");
		BigDecimal p = RootFinder.squareRoot(new BigDecimal(n
				.toString()),10);
		BigInteger roundUp = p.setScale(0, BigDecimal.ROUND_CEILING)
				.toBigIntegerExact();

		return roundUp;
	}

	/**
	 * Gets approximation of Cube Root of a BigDecimal, n, using Newton's
	 * numerical method.
	 * 
	 * @param n
	 * @param scale
	 * @return
	 */
	public static BigDecimal cubeRoot(BigDecimal n, int scale) {

		BigDecimal third = new BigDecimal(1.0 / 3.0);
		BigDecimal temp = third.multiply(n);
		BigDecimal root = third.multiply((TWO.multiply(temp).add(n.divide(
				temp.multiply(temp), scale, BigDecimal.ROUND_HALF_UP))));
		while (root.subtract(temp).abs().compareTo(EPSILON) > 0) {
			temp = root;
			root = third.multiply((TWO.multiply(temp).add(n.divide(
					temp.multiply(temp), scale, Settings.ROUNDING))));
		}
		return root.setScale(6, Settings.ROUNDING);
	}
	
	/**
	 * Calculates the x root of BigDecimal n, where x is the root argument.
	 * @param n the argument.
	 * @param root the root to calculate.
	 * @param scale
	 * @return root of BigDecimal.
	 */
	public static BigDecimal nRoot(BigDecimal n, int root, int scale) {
		float inv = 1.0f / root;
		BigDecimal r = new BigDecimal(String.valueOf(inv));
		BigDecimal temp = r.multiply(n);
		BigDecimal rmo = new BigDecimal(String.valueOf(root-1));
		BigDecimal nroot = r.multiply((rmo.multiply(temp).add(n.divide(
				temp.pow(root-1), scale, BigDecimal.ROUND_HALF_UP))));
		while (nroot.subtract(temp).abs().compareTo(EPSILON) > 0) {
			temp = nroot;
			nroot = r.multiply((rmo.multiply(temp).add(n.divide(
					temp.pow(root-1), scale, BigDecimal.ROUND_HALF_UP))));
		}
		return nroot.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
}
