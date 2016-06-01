package factoring;

import java.math.BigInteger;
import java.util.Random;

public class PollardRho {
	
	private static final Random random = new Random(System.currentTimeMillis());
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");
    private static final BigInteger FIVE = new BigInteger("5");

	public static BigInteger pollardRho(BigInteger N) {
        if (N.compareTo(BigInteger.ZERO) == 0) {
            throw new IllegalArgumentException("Trying to factor zero.");
        }
        BigInteger divisor;
        
        while(true){
			int i = 0;
			BigInteger summand = new BigInteger(N.bitLength(), random);
			BigInteger a = new BigInteger(N.bitLength(), random);
			BigInteger b = a;

			if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0)
				return TWO;

			if (N.mod(THREE).compareTo(BigInteger.ZERO) == 0)
				return THREE;

			if (N.mod(FIVE).compareTo(BigInteger.ZERO) == 0)
				return FIVE;

			a = calcValue(a, summand, N);
			b = calcValue(calcValue(b, summand, N), summand, N);
			divisor = a.subtract(b).gcd(N);

			while (i++ < 10000000 && (divisor.compareTo(BigInteger.ONE)) == 0) {
				a = calcValue(a, summand, N);
				b = calcValue(calcValue(b, summand, N), summand, N);
				divisor = a.subtract(b).gcd(N);
			}
				
			if(divisor.compareTo(BigInteger.ONE) > 0){
				if(divisor.isProbablePrime(100)){
					return divisor;
				}else{
					return pollardRho(divisor);
				}
			}
			else if(N.isProbablePrime(100)){
					return N;
			}
        	else return Factorizor.trialDivisionFactor(N);
        }
    }
	

    /**
     * Calculate the next value of n, by squaring and adding summand modulo N.
     * 
     * @param n
     * @param summand
     * @param N
     * @return next iteration of n.
     */
    private static BigInteger calcValue(BigInteger n, BigInteger summand, BigInteger N) {
        return n.multiply(n).add(summand).mod(N);
    }
}
