package transcendance;

import utils.RootFinder;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *  Gamma function, factorial.
 * @author Jon Hough
 *
 */
public class Gamma {
	
	private static BigDecimal[] constants = {
		new BigDecimal("0.99999999999980993"), 
		new BigDecimal("676.5203681218851"), 
		new BigDecimal("-1259.1392167224028"),
		new BigDecimal("771.32342877765313"), 
		new BigDecimal("-176.61502916214059"), 
		new BigDecimal("12.507343278686905"),
		new BigDecimal("-0.13857109526572012"), 
		new BigDecimal("9.9843695780195716e-6"), 
		new BigDecimal("1.5056327351493116e-7")};

	/**
	 * Factorial for long type. Returns a BigInteger equal to 
	 * n!. Only suitable for arguments well inside 32-bit int range,
	 * even though it accepts long values. For large values use an 
	 * approximation instead.
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(long n){
		
		BigInteger b = BigInteger.ONE;
		for(int i = 1; i<= n; i++){
			b = b.multiply(BigInteger.valueOf(i));
		}
		return b;
	}
	
	/**
	 * Factorial function for small positive integer values.
	 * @param n non-negative integer, less than 17.
	 * @return factorial of n, n!
	 */
	public static int factorial32(int n){
		if(n < 0 || n > 18)
			throw new IllegalArgumentException("Illegal argument for 32-bit int factorial.");
		else if(n == 0)
			return 1;
		else{
			int total = 1;
			for(int i = 1; i <=n; i++){
				total *= i;
			}
			return total;
		}
	}
	

 
	/**
	 * Finds an approximation of Gamma(D) for BigDecimal D, (sometimes (D-1)!).
	 *
	 * @param D 
	 * @return lanczos approximation of Gamma(D).
	 */
	public static BigDecimal lanczosApproximation(BigDecimal D){
		if(D.compareTo(new BigDecimal("0.5")) < 0) 
			return new BigDecimal(Math.PI).divide((BigTrigonometry.bigSin(new BigDecimal(Math.PI).multiply(D)).multiply(lanczosApproximation(BigDecimal.ONE.subtract(D)))), BigDecimal.ROUND_CEILING);
 
		D = D.subtract(BigDecimal.ONE);
		BigDecimal a = constants[0];
		BigDecimal t = D.add(new BigDecimal("7.5"));
		for(int i = 1; i < constants.length; i++){
			a = a.add(constants[i].divide(D.add(new BigDecimal(i)), 20, BigDecimal.ROUND_CEILING));// /(x+i);
		}
		return new BigDecimal(String.valueOf(Math.sqrt(2*Math.PI))).
				multiply(
						Exp.BigExp(t, D.add(new BigDecimal(0.5)))
						).multiply(
								Exp.exp(new BigDecimal("-1")
								.multiply(t))
								).multiply(a);
	}


	/**
	 * Finds an approximation of <i>Gamma(D+1)</i> for BigDecimal D, i.e. for integers <i>D!</i>.
	 * @param D
	 * @return stirling approximation of Gamma(D+1)
	 */
	public static BigDecimal stirlingApproximation(BigDecimal D){
		BigDecimal coefficient = RootFinder.squareRoot(new BigDecimal("2").multiply(new BigDecimal(Math.PI)).multiply(D), 2);
		BigDecimal power = Exp.BigExp(D.divide(Exp.E_PRECISE, 20, BigDecimal.ROUND_HALF_DOWN),D);
		return coefficient.multiply(power);
	}
}
