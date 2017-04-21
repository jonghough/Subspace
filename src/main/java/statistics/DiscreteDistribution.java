package statistics;


import java.util.ArrayList;

public interface DiscreteDistribution {

    ArrayList<Double> mode();
    double median();
    double expected();
    double variance();
    double skew();

    /**
     * Excess Kurtosis (Kurtosis -3). Measure of the "sharpness" or "bluntness" of rhw
     * distribution. i.e. a "fat-tailed" distribution, has high kurtosis, and a large
     * number of extreme values.
     * @return Excess kurtosis value
     */
    double kurtosis();
    double pmf(int i);
    double cdf(int i);

}
