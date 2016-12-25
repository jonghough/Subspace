package statistics;


import combinatorics.Binomial;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


/**
 * Implementation of <i>Hypergeometric Distribution</i>.
 */
public class HypergeometricDistribution implements DiscreteDistribution {

    private int mNum, mSuccess, mTrials;

    public HypergeometricDistribution(int N, int s, int trials){

        if(mNum < 0 || mSuccess < 0 || mTrials < 0)
            throw new IllegalArgumentException("Arguments must be non-negative.");
        mNum = N;
        mSuccess = s;
        mTrials = trials;
    }

    @Override
    public double expected() {
        return mTrials * mSuccess * 1.0 / mNum;
    }

    @Override
    public double variance() {
        return mSuccess * (mNum - mSuccess) * (mNum - mTrials) * 1.0 / (mNum * mNum * (mNum - 1));
    }

    @Override
    public double skew() {
        return 0;
    }

    @Override
    public double kurtosis() {
        return 0;
    }

    @Override
    public double pmf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        if(i > mNum) throw new IllegalArgumentException("Argument is too large.");
        if(i < 10){
            long numerator = Binomial.coefficient(mSuccess, i);
            numerator *= Binomial.coefficient(mNum - mSuccess, mTrials - i);

            long denominator = Binomial.coefficient(mNum, mTrials);
            return ((double)numerator) / denominator;
        }
        else{
            BigInteger coeff1 = Binomial.coefficient(new BigInteger(String.valueOf(mSuccess)), new BigInteger(String.valueOf(i)));
            BigInteger coeff2 = Binomial.coefficient(new BigInteger(String.valueOf(mNum - mSuccess)), new BigInteger(String.valueOf(mTrials - i)));
            BigInteger num = coeff1.multiply(coeff2);
            BigInteger denom = Binomial.coefficient(new BigInteger(String.valueOf(mNum)), new BigInteger(String.valueOf(mTrials)));

            return  new BigDecimal(num).divide(new BigDecimal(denom), 10, RoundingMode.HALF_DOWN.HALF_UP).doubleValue();
        }
    }

    @Override
    public double cdf(int i) {
        return 0;
    }


}
