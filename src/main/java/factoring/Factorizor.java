
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
 */
public class Factorizor {

    private static final Random random = new Random(System.currentTimeMillis());
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FIVE = new BigInteger("5");

	/**
	 * Factorization method to use.<br>
	 * <b>RHO</b> = Pollard rho;<br>
	 * <b>PMO</b> = P minus one;<br>
	 * <b>PPO</b> = P plus one;<br>
	 * <b>ECF</b> = Elliptic curve factorization.<br>
	 *
	 */
	public enum FactorMethod{ RHO, PMO, PPO, ECF}
    
    

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
        BigInteger d = N;
        do {
            BigInteger divisor = null;
            if (factorMethod == FactorMethod.RHO)
                divisor = PollardRho.pollardRho(d);
            else if (factorMethod == FactorMethod.PPO)
                divisor = WilliamsPPO.ppoFactor(d);
            else if (factorMethod == FactorMethod.PMO)
                divisor = PollardPMO.pmoFactor(d);
            else if (factorMethod == FactorMethod.ECF)
                divisor = BigEllipticCurve.factorLenstra(d);
            factorList.add(divisor);
            d = d.divide(divisor);
        }while(d.compareTo(BigInteger.ONE) > 0);
        return factorList;
    }

 
    /**
     * Function to retrieve list of positive integers less than N which are
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
     * Factorization by trial division. 
     * Searches all integer values up to square root of N for any factors.
     * If argument is less than 1 or greater than INT_MAX then an exception will be thrown.
     * @param N
     * @return
     */
    public static BigInteger trialDivisionFactor(BigInteger N){
    	if(N.compareTo(BigInteger.ONE) < 0) throw new IllegalArgumentException("Must have positive argument.");
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
