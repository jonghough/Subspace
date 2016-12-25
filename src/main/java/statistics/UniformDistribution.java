package statistics;


public class UniformDistribution implements DiscreteDistribution {

    private final int mLower;
    private final int mHigher;
    private final double prob;
    public UniformDistribution(int lower, int higher){
        if(higher <= lower ) throw new IllegalArgumentException("the values must be different, with the first value smaller than the second.");
        mLower = lower;
        mHigher = higher;
        prob = 1.0 / (higher - lower);
    }
    @Override
    public double expected() {
        return 0.5 * (mLower + mHigher);
    }

    @Override
    public double variance() {
        return 0;
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
        if(i < mLower || i > mHigher) throw new IllegalArgumentException("Argument our of range");
        return prob;
    }

    @Override
    public double cdf(int i) {
        if(i < mLower || i > mHigher) throw new IllegalArgumentException("Argument our of range");
        return (i - mLower) * prob;
    }
}
