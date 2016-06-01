
package factoring;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import arithmetic.JacobiSymbol;
import ellipticcurves.BigEllipticCurve;
import utils.RootFinder;

/**
 * Factorization algorithms. Contains a collection  of Integer factorization algorithms
 * including:<br>
 * Trial divison;<br>
 * Rho method;<br>
 * P+1 method;<br>
 * P-1 method;<br>
 * Elliptic curves;<br>
 * Algorithms will (attempt to) factor positive BigInteger values.Trial division will only attempt
 * factorization for 32-bit range integers. (i.e. within Integer.MAX_VALUE bound).
 * 
 * @author Jon Hough
 */
public class Factorizor {

    private static final Random random = new Random(System.currentTimeMillis());
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FIVE = new BigInteger("5");
    

	/**
	 * Factorization method to use.<br>
	 * <b>RHO</b> = Pollard rho;<br>
	 * <b>PMO</b> = P minus one;</br>
	 * <b>PPO</b> = P plus one;</br>
	 * <b>ECF</b> = Elliptic curve factorization.</br>
	 *
	 */
	public enum FactorMethod{ RHO, PMO, PPO, ECF}
	
    /**
     * Factor N using Pollard Rho algorithm. Finds cycles
     * 
     * @param N
     * @return
     */
    

    
    

    /**
     * Factors the big integer N.
     * 
     * @param N
     * @return arrayList of positive factors of N.
     */
    public static ArrayList<BigInteger> factor(BigInteger N, FactorMethod factorMethod) {

        ArrayList<BigInteger> factorList = new ArrayList<BigInteger>();
        if (N.compareTo(BigInteger.ONE) == 0)
            return factorList;
        BigInteger divisor = null;
        if(factorMethod == FactorMethod.RHO)
        	divisor = PollardRho.pollardRho(N);
        else if(factorMethod == FactorMethod.PPO)
        	divisor = WilliamsPPO.ppoFactor(N);
        else if(factorMethod == FactorMethod.PMO)
        	divisor = PollardPMO.pmoFactor(N);
        else if(factorMethod == FactorMethod.ECF)
        	divisor = BigEllipticCurve.factorLenstra(N);
        factorList.add(divisor);
        factorList.addAll(factor(N.divide(divisor), factorMethod));
        return factorList;
    }

 
    /**
     * Function to retrieve list of posititve integers less than N which are
     * coprime to N.
     * Algorithm is slow.
     * @param N
     * @return
     */
    public static ArrayList<BigInteger> calcCoprimes(BigInteger N) {
        ArrayList<BigInteger> coprimes = new ArrayList<BigInteger>();
        //start at two
        BigInteger count = BigInteger.ONE.add(BigInteger.ONE);
        while(count.compareTo(N) < 0){
            if(count.gcd(N) == BigInteger.ONE)
                coprimes.add(count);
            count = count.add(BigInteger.ONE);
        }
        return coprimes;
    }
    
    /**
     * Simple factorization by finding factors up to the square root of N. 
     * Should only be used for small values of N (in int32 range).
     * @param N
     * @return
     */
    public static ArrayList<BigInteger> factorSimple(BigInteger N){
    	ArrayList<BigInteger> factorList = new ArrayList<BigInteger>();
        if (N.compareTo(BigInteger.ONE) == 0)
            return factorList;

        BigInteger divisor = trialDivisionFactor(N);
        factorList.add(divisor);
        factorList.addAll(factorSimple(N.divide(divisor)));
        return factorList;
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
	public static BigInteger calcNext(BigInteger N, BigInteger base, BigInteger m) {
		BigInteger x = base;
		BigInteger y = base.multiply(base).subtract(TWO);
		byte[] b = m.toByteArray();
		int msbi = 0;

		for (int i = 0; i < b.length; i++) {
			if (i == 0) {
				int j = 8;
				while (j-- >= 0) {
					if (((b[0] >> j) & 1) == 1) {
						msbi = j;
						break;
					}
				}
				if (msbi > 0) {
					for (int k = j - 1; k >= 0; k--) {
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
				for (int k = 8; k >= 0; k--) {
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
    
    /**
     * Factorization by trial division. 
     * Searches all integer values up to square root of N for any factors.
     * If argument is less than 1 or greater than INT_MAX then an exception will be thrown.
     * @param N
     * @return
     */
    public static BigInteger trialDivisionFactor(BigInteger N){
    	if(N.compareTo(BigInteger.ONE) < 0) throw new IllegalArgumentException("Must have positive arguement.");
    	if(N.compareTo( new BigInteger(String.valueOf(Integer.MAX_VALUE)))> 0)
    		throw new IllegalArgumentException("Argument may be too large for simple factorization algorithm.");
    	if(N == BigInteger.ONE) return BigInteger.ONE;
    	if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0)
            return TWO;
        
        if (N.mod(THREE).compareTo(BigInteger.ZERO) == 0)
            return THREE;

        if (N.mod(FIVE).compareTo(BigInteger.ZERO) == 0)
            return FIVE;
        
    	BigInteger count = new BigInteger("7");
    	BigInteger sqrt = RootFinder.squareRoot(new BigDecimal(N), 10).toBigInteger().add(BigInteger.ONE);
    	while(count.compareTo(sqrt) < 0){
    		if(N.mod(count).compareTo(BigInteger.ZERO) == 0){
    			return trialDivisionFactor(count);
    		}

    		count = count.add(BigInteger.ONE);
    	}
    	return N;
    }
}