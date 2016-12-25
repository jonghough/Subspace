package statistics;


public class UniformContinuousDistribution implements ContinuousDistribution {

    private double mLower, mUpper;
    public UniformContinuousDistribution(double lower, double upper){
        if(upper <= lower ) throw new IllegalArgumentException("the values must be different, with the first value smaller than the second.");

        mLower = lower;
        mUpper = upper;
    }

    @Override
    public double expected() {
        return 0.5 * (mUpper + mLower);
    }

    @Override
    public double variance() {
        return (mUpper - mLower) * (mUpper - mLower) / 12.0;
    }

    @Override
    public double skew() {
        return 0;
    }

    @Override
    public double kurtosis() {
        return -6.0 / 5.0;
    }

    @Override
    public double pdf(double x) {
        return 1.0 / (mUpper - mLower);
    }

    @Override
    public double cdf(double k) {
        if(k < mLower || k > mUpper) throw new IllegalArgumentException("Argument our of range");
        return (k - mLower) * 1.0 / (mUpper - mLower);
    }

    @Override
    public double entropy() {
        return Math.log(mUpper - mLower);
    }
}
