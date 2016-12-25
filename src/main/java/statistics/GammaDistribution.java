package statistics;


import transcendence.Gamma;

import java.math.BigDecimal;

public class GammaDistribution implements ContinuousDistribution {

    private int mShape;
    private double mRate;

    public GammaDistribution(int shape, int rate){
        mShape = shape;
        mRate = rate;
    }
    @Override
    public double expected() {
        return mShape * 1.0 / mRate;
    }

    @Override
    public double variance() {
        return mShape * 1.0 / (mRate * mRate);
    }

    @Override
    public double skew() {
        return 2.0 / Math.sqrt(mShape);
    }

    @Override
    public double kurtosis() {
        return 0;
    }

    @Override
    public double pdf(double x) {
        return Math.pow(x, mShape - 1) * Math.exp(-x * mRate) / Gamma.factorial32(mShape);
    }

    @Override
    public double cdf(double k) {
        return 0;
    }

    @Override
    public double entropy() {
        return 0;
    }
}
