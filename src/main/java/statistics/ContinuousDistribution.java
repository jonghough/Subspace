package statistics;

/**
 *
 */
public interface ContinuousDistribution {

    /**
     * Expected value, or mean value of the distribution.
     * @return the expected value.
     */
    double expected();

    /**
     * Variance of the distribution.
     * @return variance
     */
    double variance();

    /**
     * Skewness, measure of left or right bias about the mean.
     * @return
     */
    double skew();

    /**
     * Gives the <i>Excess Kurtosis</i> of the distribution.
     * @return excess kurtosis
     */
    double kurtosis();

    /**
     * Gives the <i>probability density function </i> value for the given argument, for this
     * probability distribution.
     * @param x
     * @return the value of the <i>pmf</i> for the given srguemnt, x.
     */
    double pdf(double x);

    /**
     *
     * @param k
     * @return
     */
    double cdf(double k);


    double entropy();

}
