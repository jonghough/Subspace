package statistics;


import transcendence.Digamma;
import transcendence.Gamma;

import java.math.BigDecimal;

public class GammaDistribution implements ContinuousDistribution {

    private int mShape;
    private double mRate;

    public GammaDistribution(int shape, int rate) {
        if (mShape <= 0)
            throw new IllegalArgumentException(String.format("Shape argument must be positive. Value given: %s.", mShape));
        if (mRate <= 0)
            throw new IllegalArgumentException(String.format("Rate argument must be positive. Value given: %s.", mRate));
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
        return mShape + Math.log(1.0 / mRate) + Math.log(Gamma.factorial32(mShape)) + (1 - mShape) * Digamma.digamma(mShape);
    }
}
