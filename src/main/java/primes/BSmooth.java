package primes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import factoring.Factorizor;
import utils.Exp;
import utils.RootFinder;

/**
 * Class holds B-smooth static methods.
 * @author Jon Hough
 *
 */
public class BSmooth {

    /**
     * Tests if the integer N is B-smooth, for integer B.<br>
     * That is, all prime factors of N ar eless than B.
     * @param N
     * @param B
     * @return true if N is B smooth, false otherwise.
     */
    public static boolean isBSmooth(BigInteger N, BigInteger B){
        ArrayList<BigInteger> factors = Factorizor.factor(N, Factorizor.FactorMethod.RHO);
        boolean bsmooth = true;
        for(BigInteger bi : factors){
            if(bi.compareTo(B) > 0){
                bsmooth = false;
                break;
            }
        }
        return bsmooth;
    }
    
    /**
     * Returns the number of B-smooth integers in range (1, N] for BigInteger N.
     * 
     * @param N
     * @param B
     * @return the number of B smooth integers in the range.
     */
    public static BigInteger psi(BigInteger N, BigInteger B){
        if(N.compareTo(BigInteger.ONE) <= 0){
            throw new IllegalArgumentException("Cannot have N less than or equal to 1.");
        }
        BigInteger counter = new BigInteger("2");
        BigInteger num = BigInteger.ZERO;
        while(counter.compareTo(N) <= 0){
            if(isBSmooth(counter, B))
            	num = num.add(BigInteger.ONE);
            	counter = counter.add(BigInteger.ONE);
        }
        return num;
    }
    
    
    /**
     * Calculates the function L(X) of a BigInteger X.
     * L(X) defined as exp(sqrt(ln(x) * ln(ln(x))).
     * <br>
     * An Introduction to Mathematical Cryptography page 147.
     * @param X
     * @return L(X)
     */
    public static BigDecimal L(BigInteger X){
    	final int scale = 10; 
        BigDecimal lnX = Exp.ln(new BigDecimal(X), scale);
        BigDecimal lnlnX = Exp.ln(lnX,scale);
        BigDecimal expDecimal =  RootFinder.squareRoot(lnX.multiply(lnlnX), scale);
        BigInteger expInt = expDecimal.toBigInteger();
        BigDecimal l = Exp.exp(new BigDecimal("2.7182818"), expInt);
        return l;
    }
    
    
    
    
}
