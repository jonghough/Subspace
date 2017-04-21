package transcendence;

import utils.RootFinder;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *  Gamma function, factorial.
 *
 */
public class Gamma {
	
	private static BigDecimal[] constants = {
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
			return new BigDecimal(Math.PI).divide((BigTrigonometry.bigSin(new BigDecimal(Math.PI).
					multiply(D)).multiply(lanczosApproximation(BigDecimal.ONE.subtract(D)))), BigDecimal.ROUND_HALF_DOWN);
 
		D = D.subtract(BigDecimal.ONE);
		BigDecimal a = new BigDecimal("0.99999999999980993");
		BigDecimal t = D.add(new BigDecimal("7.5"));
		for(int i = 0; i < constants.length; i++){
			a = a.add(constants[i].divide(D.add(new BigDecimal(i+1)), 100, BigDecimal.ROUND_HALF_DOWN));
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
	 * Calculates an approximation to <code>gamma(D)</code>, using Lanczos algorithm and the fact that <br>
	 *     <code>gamma(D) = D * gamma(D-1)</code>.
	 * @param D BigDecimal d.
	 * @return Gamma(D)
	 */
	public static BigDecimal gamma(BigDecimal D){
		if(D.compareTo(new BigDecimal(0.5)) < 0)
			return new BigDecimal(Math.PI).divide((BigTrigonometry.bigSin(new BigDecimal(Math.PI).
					multiply(D)).multiply(gamma(BigDecimal.ONE.subtract(D)))), BigDecimal.ROUND_HALF_DOWN);
		else if(D.compareTo(BigDecimal.ONE) < 0){
			return lanczosApproximation(D);
		}
		else if(D.compareTo(BigDecimal.ONE) == 0){
			return BigDecimal.ONE;
		}
		else {
			BigDecimal f = D.subtract(BigDecimal.ONE); //gamma(d) = f !
			BigDecimal g = f;
			f = f.subtract(BigDecimal.ONE);
			while (f.compareTo(BigDecimal.ONE) > 0) {
				g = g.multiply(f);
				f = f.subtract(BigDecimal.ONE);
			}
			f = f.add(BigDecimal.ONE); //because gamma(f+1) = f!
			BigDecimal l = lanczosApproximation(f);
			return g.multiply(l);
		}
	}


	/**
	 * Returns the <i>incomplete gamma function</i>, <i>IG(a,x)</i>.
	 * Algorithm taken from
	 * <br>
	 *     <i>Numerical Recipes 6.2, p. 216</i>
	 * @param a
	 * @param x
	 * @return incomplete <i>gamma</i> function
	 */
	public static BigDecimal incompleteGamma(BigDecimal a, BigDecimal x){
		return gammq(a,x).divide(gamma(a),20, BigDecimal.ROUND_HALF_DOWN);
	}

	private static BigDecimal gammp(BigDecimal a, BigDecimal x){ //gammp
		if(a.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Parameter a must be greater than zero.");
		if(x.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Parameter x must be non-negative.");

		if(x.compareTo(a.add(BigDecimal.ONE)) < 0){
			return gser(a, x);
		}
		else{
			return BigDecimal.ONE.subtract(gcf(a,x));
		}


	}

	private static BigDecimal gammq(BigDecimal a, BigDecimal x){ //gammq
		if(a.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Parameter a must be greater than zero.");
		if(x.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Parameter x must be non-negative.");

		if(x.compareTo(a.add(BigDecimal.ONE)) < 0){
			return BigDecimal.ONE.subtract(gser(a, x));
		}
		else{
			return gcf(a,x);
		}
	}

	private static BigDecimal gser(BigDecimal a, BigDecimal x){ //gser
		BigDecimal logGamma = Exp.ln(gamma(a), 20);
		if(x.compareTo(BigDecimal.ZERO) < 0){
			if(x.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("negative value.");
			else{
				return BigDecimal.ZERO;
			}
		}
		else{
			int max = 100;
			BigDecimal del = BigDecimal.ONE.divide(a,20,BigDecimal.ROUND_HALF_DOWN);
			BigDecimal sum = del;
			BigDecimal ap = a;

			for(int n = 1; n <= max;n++){
				ap = ap.add(BigDecimal.ONE);
				del = del.multiply(x.divide(ap, 20, BigDecimal.ROUND_HALF_DOWN));
				sum = sum.add(del);
				if(ap.abs().compareTo(sum.abs().multiply(new BigDecimal("1.0E-7"))) < 0){
					return sum.multiply(Exp.exp(x.negate().add(a.multiply(Exp.ln(x, 20))).subtract(logGamma)));
				}
			}
		}
		throw new RuntimeException("Value a may be too large");

	}


	private static BigDecimal gcf(BigDecimal a, BigDecimal x){
		BigDecimal logGamma = Exp.ln(gamma(a), 20);
		BigDecimal b = x.add(BigDecimal.ONE).subtract(a);
		BigDecimal c = BigDecimal.ONE.divide(new BigDecimal("1.0E-30"),20,BigDecimal.ROUND_HALF_DOWN);
		BigDecimal d = BigDecimal.ONE.divide(b,20,BigDecimal.ROUND_HALF_DOWN);
		BigDecimal h = d;

		int n = 1;
		int max = 100;
		for(n = 1; n <= max;n++){
			BigDecimal m = new BigDecimal(n);
			BigDecimal an = m.negate().multiply(m.subtract(a));
			b=b.add(new BigDecimal("2"));
			d = an.multiply(d).add(b);
			if(d.abs().compareTo(new BigDecimal("1.0E-30")) < 0){
				d = new BigDecimal("1.0E-30");
			}
			c = b.add(an.divide(c, 20, BigDecimal.ROUND_HALF_DOWN));
			if(c.abs().compareTo(new BigDecimal("1.0E-30")) < 0){
				c = new BigDecimal("1.0E-30");
			}
			d = BigDecimal.ONE.divide(d,20, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal del = d.multiply(c);
			h = h.multiply(del);
			if(del.subtract(BigDecimal.ONE).abs().compareTo(new BigDecimal("1.0E-7")) < 0)
				break;

		}
		return Exp.exp(x.negate().add(a.multiply(Exp.ln(x, 20))).subtract(logGamma)).multiply(h);
	}

	private static BigDecimal errf(BigDecimal x){
		BigDecimal g = gammp(new BigDecimal("0.5"), x.multiply(x));
		if(x.compareTo(BigDecimal.ZERO) < 0) return g.negate();
		else return g;
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
