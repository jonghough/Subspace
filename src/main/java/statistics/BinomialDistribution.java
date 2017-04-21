package statistics;


import combinatorics.Binomial;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Implementation of Binomial Distribution, where
 * <p>
 * <code>new BinomialDistribution(n,p);</code>
 * </p>
 * creates a binomial probability distribution where <code>p</code> is
 * the probability of a single success from <code>n</code> trials.
 */
public class BinomialDistribution implements DiscreteDistribution {
    private final int N;
    private final double P;

    public BinomialDistribution(int n, double p) {
        N = n;
        P = p;
    }

    public int getN() {
        return N;
    }

    public double getP() {
        return P;
    }

    @Override
    public ArrayList<Double> mode() {
        double v = (N + 1) * P;
        ArrayList<Double> modeList = new ArrayList<>();
        if (v - (long) v < 0.00001) {
            modeList.add((N + 1) * P);
            modeList.add((N + 1) * P - 1);
        } else if (P == 1.0) {
            modeList.add((double) N);
        } else {
            modeList.add(Math.floor((N + 1) * P));
        }
        return modeList;
    }

    @Override
    public double median() {
        return Math.ceil(N * P);
    }

    @Override
    public double expected() {
        return N * P;
    }

    @Override
    public double variance() {
        return expected() * (1 - P);
    }

    @Override
    public double skew() {
        return (1 - 2 * P) / Math.sqrt(N * P * (1 - P));
    }

    @Override
    public double kurtosis() {
        return (1 - 6 * P * (1 - P)) / (N * P * (1 - P));
    }

    @Override
    public double pmf(int i) {
        if (i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        if (i > N) throw new IllegalArgumentException("Argument is too large.");
        if (i < 10) {
            return Binomial.coefficient(N, i) * Math.pow(P, i) * Math.pow(1 - P, N - i);
        } else {
            BigInteger coeff = Binomial.coefficient(new BigInteger(String.valueOf(N)), new BigInteger(String.valueOf(i)));
            BigDecimal prob = new BigDecimal(Math.pow(P, i) * Math.pow(1 - P, N - i));
            return new BigDecimal(coeff).multiply(prob).doubleValue();

        }
    }

    @Override
    public double cdf(int i) {
        if (i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        if (i > N) throw new IllegalArgumentException("Argument is too large.");

        double total = 0;
        for (int j = 0; j <= i; j++) {
            total += pmf(j);
        }
        return total;
    }
}
