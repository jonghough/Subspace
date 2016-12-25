package statistics;


import transcendence.Gamma;

/**
 *
 */
public class PoissonDistribution implements DiscreteDistribution {

    private final double mLambda;

    public PoissonDistribution(double lambda){
        if(lambda < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        mLambda = lambda;
    }

    @Override
    public double expected() {
        return mLambda;
    }

    @Override
    public double variance() {
        return mLambda;
    }

    @Override
    public double skew() {
        return Math.pow(mLambda, 0.5);
    }

    @Override
    public double kurtosis() {
        return 1.0 / mLambda;
    }

    @Override
    public double pmf(int i) {
        return Math.pow(mLambda, i) * Math.exp(-mLambda) / Gamma.factorial32(i);
    }

    @Override
    public double cdf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        double total = 0;
        for(int j = 0; j <= i; j++){
            total += Math.pow(mLambda, j) * Math.exp(-mLambda) / Gamma.factorial32(j);
        }
        return total;
    }
}
