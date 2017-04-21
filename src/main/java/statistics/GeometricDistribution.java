package statistics;

import java.util.ArrayList;

/**
 * <i>Geometric Distribution</i> measures the probability of success
 * after <i>k</i> trials, where each trial has independent probability
 * <i>p</i>.<br>
 * e.g. <i>How many rolls of a dice are needed before I roll a 6?</i>
 */
public class GeometricDistribution implements DiscreteDistribution {

    private final double P;
    public GeometricDistribution(double p){
        P = p < 0 ? 0 : p > 1.0 ? 1.0 : p;
    }

    @Override
    public ArrayList<Double> mode() {
        ArrayList<Double> modes = new ArrayList<Double>();
        modes.add(1.0);
        return modes;
    }

    @Override
    public double median() {
        double v = Math.ceil(-1.0 / Math.log(1-P)/ Math.log(2));
        return v;
    }

    @Override
    public double expected() {
        return 1.0 / P;
    }

    @Override
    public double variance() {
        return (1 - P) / (P * P);
    }

    @Override
    public double skew() {
        return (2 - P) / Math.sqrt(1 - P);
    }

    @Override
    public double kurtosis() {
        return 6.0 + (P * P ) / (1 - P);
    }

    @Override
    public double pmf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument our of range");
        return Math.pow(1.0 - P, i - 1) * P;
    }

    @Override
    public double cdf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument our of range");
        double total = 0;
        for(int j = 0; j <= i; j++) {
            total += Math.pow(1.0 - P, i - 1) * P;
        }
        return total;
    }
}
