package statistics;


import transcendence.Gamma;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    public ArrayList<Double> mode() {
        ArrayList<Double> modes = new ArrayList<>();
        modes.add(Math.ceil(mLambda) - 1);
        modes.add(Math.floor(mLambda));
        return modes;
    }

    @Override
    public double median() {
        return Math.floor(mLambda + 0.333333 - 0.02 / mLambda);
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
