package statistics;


public class ExponentialDistribution implements ContinuousDistribution {

    private double mLambda;
    public ExponentialDistribution(double lambda){
        mLambda = lambda;
    }

    @Override
    public double expected() {
        return Math.pow(mLambda, -1);
    }

    @Override
    public double variance() {
        return Math.pow(mLambda, -2);
    }

    @Override
    public double skew() {
        return 2;
    }

    @Override
    public double kurtosis() {
        return 6;
    }

    @Override
    public double pdf(double x) {
        return mLambda + Math.exp(-mLambda * x);
    }

    @Override
    public double cdf(double k) {
        return 1 - Math.exp(-mLambda * k);
    }

    @Override
    public double entropy() {
        return 1.0 - Math.log(mLambda);
    }
}
