
package transcendence;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Functions for calculating exponents.
 *
 */
public class Exp {

	/* more precise decimal expansion of e */
	/* default*/ static final BigDecimal E_PRECISE =
			new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427427466391932003059921817413596629043572900334295260595630738132328627943490763233829880753195251019011573834187930702154089149");

    public static final BigDecimal PI_PRECISE =
            new BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196");
	public static final BigInteger TWO = new BigInteger("2");

    /**
     * Base 2 log of e (~ 2.71828...)
     */
    public static final BigDecimal LOG_2_E = new BigDecimal("1.4426950408889634073599246810018921374266459541529859341354494069311092191811850798855266228935063444969975183096");
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
    	return BigExp(D, new BigDecimal(exponent));
    }
    
    // Taylor series expand the exponential function around val, up to the max term.
    // Since val *should* be less than 1, this should converge quickly.
    private static BigDecimal taylorExpandExp(BigDecimal val, int max){
    	int i;
    	BigDecimal tot = BigDecimal.ZERO;
    	for(i = 0; i < max; i++){
    		tot = tot.add(taylorVal(val,i, 200));
    	}
    	return tot;
    }
    
    // Single term in Taylor expansion.
    private static BigDecimal taylorVal(BigDecimal val, int exponent, int scale){
    	
    	BigDecimal power = exp(val,new BigInteger(String.valueOf(exponent)));
    	
    	try{
    		BigDecimal res = power.divide(new BigDecimal(factorial(new BigInteger(String.valueOf(exponent)))), scale, BigDecimal.ROUND_HALF_DOWN);
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

    //quick factorial calculator.
    private static BigInteger factorial(BigInteger n){
        if(n.compareTo(BigInteger.ZERO) < 0) throw new IllegalArgumentException("Must be non-negative");
        if(n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0){
            return BigInteger.ONE;
        }
        else{
            BigInteger total = BigInteger.ONE;
            BigInteger x = n;
            while(x.compareTo(BigInteger.ONE) > 0){
                total = total.multiply(x);
                x = x.subtract(BigInteger.ONE);
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

    	p2 = taylorExpandExp((mantissa).multiply(ln(D, 300)), 100);
    	p1 = p1.multiply(p2);
    	return p1;
    }

    /**
     * Calculates the base 2 logarithm of a Bigdecimal N.
     * 
     * @param X BigDecimal to find log
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
            throw new IllegalArgumentException("X must be positive "+X.toPlainString());
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
     * returned value is inaccurate.
     * @param X
     * @return approximation of pi(X)
     */
    public static BigInteger approxPrimesLessThan(BigDecimal X) {
        if (X.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("X must be positive");
        return X.divide(ln(X,10), 10, BigDecimal.ROUND_HALF_DOWN).toBigInteger();
    }

}
