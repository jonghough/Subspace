package statistics;


import combinatorics.Binomial;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BinomialDistribution implements DiscreteDistribution {
    private final int N;
    private final double P;

    public BinomialDistribution(int n, double p){
        N = n;
        P = p;
    }

    public int getN(){
        return N;
    }

    public double getP(){
        return P;
    }

    @Override
    public double expected() {
        return N*P;
    }

    @Override
    public double variance() {
        return expected() * (1-P);
    }

    @Override
    public double skew() {
        return (1 - 2 * P) / Math.sqrt(N * P * ( 1 - P));
    }

    @Override
    public double kurtosis() {
        return (1 - 6 * P * (1 - P)) / (N * P * (1 - P));
    }

    @Override
    public double pmf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        if(i > N) throw new IllegalArgumentException("Argument is too large.");
        if(i < 10){
            return Binomial.coefficient(N, i) * Math.pow(P, i) * Math.pow(1 - P, N - i);
        }
        else{
            BigInteger coeff = Binomial.coefficient(new BigInteger(String.valueOf(N)), new BigInteger(String.valueOf(i)));
            BigDecimal prob =  new BigDecimal(Math.pow(P, i) * Math.pow(1 - P, N - i));
            return new BigDecimal(coeff).multiply(prob).doubleValue();

        }
    }

    @Override
    public double cdf(int i) {
        if(i < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        if(i > N) throw new IllegalArgumentException("Argument is too large.");

        double total = 0;
        for(int j = 0; j <= i; j++){
            total += pmf(j);
        }
        return total;
    }
}
