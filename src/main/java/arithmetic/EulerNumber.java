package arithmetic;


import combinatorics.Binomial;
import transcendence.BigTrigonometry;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <i>Euler numbers</i> are defined as the coefficients
 * of the Taylor series expansion of<br>
 *     <i>1 / cosh(x)</i>.<br>
 *
 */
public class EulerNumber {

    public static BigInteger en(int x){
        if(x < 0) throw new IllegalArgumentException("Argument must be non-negative");
        else if(x % 2 == 1) return BigInteger.ZERO;
        else if(x == 0) return BigInteger.ONE;
        else{
            int k = 1;
            final BigInteger minusOne = new BigInteger("-1");
            final BigInteger two = new BigInteger("2");
            BigDecimal sum = BigDecimal.ZERO;
            for(k=1;k <= x + 1; k+=2){
                BigInteger f = minusOne.add(minusOne.pow(k));
                BigInteger mul = minusOne.pow((k-1) / 2);
                BigInteger bk = new BigInteger(String.valueOf(k));
                for(int j=0;j<=k;j++){
                    BigInteger bj = new BigInteger(String.valueOf(j));
                    long binomial = Binomial.coefficient(k,j);

                    BigInteger pwr = bk.subtract(two.multiply(bj)).pow(x + 1);
                    BigInteger denominator = two.pow(k).multiply(bk);

                    BigInteger summand = new BigInteger(String.valueOf(binomial)).multiply(pwr).multiply(minusOne.pow(j));

                    BigDecimal dsum = new BigDecimal(summand.multiply(mul));
                    BigDecimal dden = new BigDecimal(denominator);
                    sum = sum.add(dsum.divide(dden, 20, BigDecimal.ROUND_HALF_DOWN));
                }
            }
            return sum.toBigIntegerExact();
        }
    }
}
