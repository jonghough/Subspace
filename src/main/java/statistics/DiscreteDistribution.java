package statistics;


public interface DiscreteDistribution {

    double expected();
    double variance();
    double skew();
    double kurtosis();
    double pmf(int i);
    double cdf(int i);

}
