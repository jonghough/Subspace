
package transcendance;

import utils.RootFinder;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Functions for calculating exponents.
 * 
 * @author Jon Hough
 */
public class Exp {

	/* more precise decimal expansion of e */
	private static final BigDecimal E_PRECISE =
			new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274");
   
	public static final BigInteger TWO = new BigInteger("2");

    /**
     * Base 2 log of e (~ 2.71828...)
     */
    public static final BigDecimal LOG_2_E = new BigDecimal("1.4426950408889634073599246810017822653278448610936510082308932567674020219783415086567401885986328125");

    /**
     * Base 2 log of 10
     */
    public static final BigDecimal LOG_2_10 = new BigDecimal("3.32192809489");
    /**
     * Euler's constant as a BigDecimal.
     */
    public static final BigDecimal E_CONST = new BigDecimal(Math.E);
    
 
    /**
     * Calculates the base^exponent.
     * 
     * @param base
     * @param exponent
     * @return base^exponent
     */
    public static BigDecimal exp(BigDecimal base, BigInteger exponent) {
        if (exponent.compareTo(BigInteger.ZERO) < 0) {
            return BigDecimal.ONE.divide(exp(base, new BigInteger("-1").multiply(exponent)), 100, BigDecimal.ROUND_HALF_DOWN);
        }
        if (exponent.compareTo(BigInteger.ZERO) == 0) {
            return new BigDecimal("1");
        }
        else if (exponent.compareTo(BigInteger.ONE) == 0) {
            return base;
        }
        else {
            if (exponent.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
                return exp(base.pow(2), exponent.divide(TWO));
            }
            else {
                return base
                        .multiply(exp(base.pow(2), exponent.subtract(BigInteger.ONE).divide(TWO)));
            }
        }
    }
    
    
    /**
     * Calculates e^exponent, where e is the base of
     * natural logarithms.
     * @param exponent
     * @return exponential of exponent.
     */
    public static BigDecimal exp(BigDecimal exponent){
    	return BigExp(E_PRECISE, exponent);
    }
    
    

    
    /**
     *  Calculates D^exponent, for BigDecimal D.
     * @param D
     * @param exponent
     * @return
     */
    public static BigDecimal BigExp(BigDecimal D, double exponent){
    	long intPart = (long)exponent;
    	BigDecimal p1 = exp(D, BigInteger.valueOf(intPart));
    	BigDecimal p2 = BigDecimal.ONE;
    	double mantissa = exponent - intPart;
    	int i = 2;
    	final int scale = 20;
    	BigDecimal x = D;
    	for(i = 1; i < 20; i++){ 
    		long m = (long)(Math.pow(10.0, i) * mantissa);
    		m = m&1;
    		x = RootFinder.nRoot(x, 10, scale);
    		if(m > 0)
    			p2 = p2.multiply(exp(x,BigInteger.valueOf(m)));
    	}
    	
    	p1 = p1.multiply(p2);
    	return p1;
    }
    
    // Taylor series expand the exponential function around val, up to the max term.
    // Since val *should* be less than 1, this should converge quickly.
    private static BigDecimal taylorExpandExp(BigDecimal val, int max){
    	int i;
    	BigDecimal tot = BigDecimal.ZERO;
    	for(i = 0; i < max; i++){
    		tot = tot.add(taylorVal(val,i, 100));
    	}
    	return tot;
    }
    
    // Single term in Taylor expansion.
    private static BigDecimal taylorVal(BigDecimal val, int exponent, int scale){
    	
    	BigDecimal power = exp(val,new BigInteger(String.valueOf(exponent)));
    	
    	try{
    		BigDecimal res = power.divide(new BigDecimal(factorial(exponent)), scale, BigDecimal.ROUND_HALF_DOWN);
    		return res;
    	}
    	catch(ArithmeticException e){
    		return BigDecimal.ZERO;
    	}
    }
    
    //quick factorial calculator.
    private static long factorial(int n){
    	if(n < 0) throw new IllegalArgumentException("Must be non-negative");
    	if(n == 0 || n == 1){
    		return 1L;
    	}
    	else{
    		long total = 1;
    		int x = n;
    		while(x > 1){
    			total = total * x;
    			x--;
    		}
    		return total;
    	}
    }

    
    /**
     *  Calculates D^exponent, where D and exponent are both BigDecimals.
     * @param D
     * @param exponent
     * @return
     */
    public static BigDecimal BigExp(BigDecimal D, BigDecimal exponent){
    	BigInteger intPart = exponent.toBigInteger();
    	BigDecimal p1 = exp(D, intPart);
    	BigDecimal p2 = BigDecimal.ONE;
    	BigDecimal mantissa = exponent.subtract(new BigDecimal(intPart));

    	p2 = taylorExpandExp((mantissa).multiply(ln(D,100)), 100);
    	p1 = p1.multiply(p2);
    	return p1;
    }

    /**
     * Calculates the base 2 logarithm of a Bigdecimal N.
     * 
     * @param N BigDecimal to find log
     * @param scale
     * @return Base 2 logarithm of N.
     */
    public static BigDecimal log2(BigDecimal X, int scale) {
        if (X.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("X must be positive");
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal mantissa = new BigDecimal("0.5");
        final BigDecimal TWOD = new BigDecimal(TWO);
        while (X.compareTo(BigDecimal.ONE) < 0 || X.compareTo(TWOD) >= 0) {
            while (X.compareTo(BigDecimal.ONE) < 0) {
                X = X.multiply(TWOD);
                result = result.subtract(BigDecimal.ONE);
            }
            while (X.compareTo(TWOD) >= 0) {
                X = X.divide(TWOD, scale, BigDecimal.ROUND_HALF_DOWN);
                result = result.add(BigDecimal.ONE);
            }
        }

        int count = scale;
        while (count-- > 0) {
            X = X.multiply(X);
            if (X.compareTo(TWOD) >= 0) {
                X = X.divide(TWOD, scale, BigDecimal.ROUND_HALF_DOWN);
                result = result.add(mantissa);
            }
            mantissa = mantissa.divide(TWOD, scale, BigDecimal.ROUND_HALF_DOWN);
        }
        return result;
    }

    /**
     * Calculates the natural logarithm of the BigDecimal X.
     * 
     * @param X
     * @param scale
     * @return the natural logarithm of X
     */
    public static BigDecimal ln(BigDecimal X, int scale) {
        if (X.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("X must be positive");
        return log2(X, scale).divide(LOG_2_E, scale, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Calculates the base 10 logarithm of BigDecimal X.
     * 
     * @param X
     * @param scale
     * @return Base 10 log of X
     */
    public static BigDecimal log10(BigDecimal X, int scale) {
        if (X.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("X must be positive");
        return log2(X, scale).divide(LOG_2_10, scale, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Calculates an approximation for the number of primes less than X.
     * Approximation is X/Ln(X), asymptotic to pi(X). For small X,
     * returned vaslue is inaccurate.
     * @param X
     * @return approximation of pi(X)
     */
    public static BigInteger approxPrimesLessThan(BigDecimal X) {
        if (X.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("X must be positive");
        return X.divide(ln(X,10), 10, BigDecimal.ROUND_HALF_DOWN).toBigInteger();
    }
    
    /**
     * Calculates base^exponent modulo (modulus).
     * @param base
     * @param exponent
     * @param modulus
     * @return
     */
    public static BigInteger expModular(BigInteger base, BigInteger exponent, BigInteger modulus){
        BigInteger val = BigInteger.ONE;
        BigInteger counter = val;
        while(counter.compareTo(exponent) <= 0){
            val = val.multiply(base);
            counter = counter.add(BigInteger.ONE).mod(modulus);
        }
        return val;
    }
}
