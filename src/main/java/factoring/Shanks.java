package factoring;


import utils.BigUtils;
import utils.RootFinder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * Implementation of Shanks Square Forms factorization algorithm.
 * Incomplete
 */
public class Shanks {
    private int multipliers[] = {
            1, 2, 3, 5, 6, 7, 10, 11, 13, 14,
            15, 17, 19, 21, 22, 23, 26, 29, 30,
            31, 33, 34, 35, 37, 38, 39, 41, 42,
            43, 46, 47, 51, 53, 55, 57, 58, 59,
            61, 62, 65, 66, 67, 69, 70, 71, 73,
            74, 77, 78, 79, 82, 83, 85, 86, 87,
            89, 91, 93, 94, 95, 97, 101, 102, 103,
            105, 106, 107, 109, 110, 111, 113,112,114,8,9,12,15
    };

    private Random random = new Random();
    private int multiplierBitLen = 40;
    private BigInteger n, mul, Pim1, Pi, Qim1, Qi, Qip1, rKN;

    public Shanks(BigInteger N, int multiplier) {
        n = N;
        multiplierBitLen = multiplier;
        mul = new BigInteger(multiplierBitLen, random).add(BigInteger.ONE);

    }

    public BigInteger factor() {

        int j = 0;
        while (j < multipliers.length) {
            int i = 0;
            mul = new BigInteger(String.valueOf(multipliers[j])); //5, random).add(BigInteger.ONE);
            System.out.println(">>>> mul is  " + mul);
            Pi = RootFinder.squareRoot(new BigDecimal(n.multiply(mul)), 10).toBigInteger();
            rKN = Pi;
            Pim1 = BigInteger.ONE;

            Qi = BigInteger.ONE;
            Qip1 = n.multiply(mul).subtract(Pi.multiply(Pi));
            while (++i < 10000) {


                if (i % 2 == 0) {
                    if (Qip1.compareTo(BigInteger.ONE) <= 0) break;

                    if (RootFinder.isPerfectSquare(Qip1)) {
                        BigInteger rQi = RootFinder.squareRoot(new BigDecimal(Qip1), 10).toBigInteger();
                        System.out.println("ROOT OF Qi+1 is >>>>>>>>>>>>> " + rQi);
                        BigInteger factor = sub1(Pi, rQi);
                        if (factor == null) {
                            break;
                        } else {
                            return factor;
                        }

                    }
                }


                Pim1 = Pi;
                Qim1 = Qi;
                Qi = Qip1;

                BigInteger bi = new BigDecimal(Pi.add(Pim1)).divide(new BigDecimal(Qi), BigDecimal.ROUND_HALF_DOWN).toBigInteger();
                Pi = bi.multiply(Qi).subtract(Pim1);
                Qip1 = Qim1.add(bi.multiply(Pim1.subtract(Pi)));
                j++;
            }
        }
        throw new FactorFailException("Failed to factor with Shanks Square Forms Algorithm", n, 0);

    }


    private BigInteger sub1(BigInteger Pval, BigInteger rQVal) {
        int i = 0;

        BigInteger bi = new BigDecimal(rKN.subtract(Pval)).divide(new BigDecimal(rQVal), BigDecimal.ROUND_HALF_DOWN).toBigInteger();
        Pi = bi.multiply(rQVal).add(Pval);
        Qi = rQVal;
        Qip1 = mul.multiply(n).subtract(Pi.multiply(Pi)).divide(rQVal);

        BigInteger prevQ, prevP;

        while (i++ < 10000) {
            prevQ = Qi;
            prevP = Pi;
            Qi = Qip1;
            bi = rKN.add(prevP).divideAndRemainder(Qi)[0];
            Pi = bi.multiply(Qi).subtract(prevP);
            Qip1 = prevQ.add(bi.multiply(prevP.subtract(Pi)));

            if (Pi.compareTo(prevP) == 0) {
                System.out.println("factor of " + n + ", (maybe not prime) found  " + Pi);
                BigInteger gcd = n.gcd(Pi);
                if (gcd.compareTo(BigInteger.ONE) > 0 && gcd.compareTo(n) < 0) {
                    if (gcd.isProbablePrime(100)) {
                        return gcd;
                    } else {
                        Shanks sh = new Shanks(gcd, multiplierBitLen);
                        return sh.factor();
                    }
                }
            }


        }
        return null;
    }
}
