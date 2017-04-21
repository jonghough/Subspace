package statistics;


public interface ContinuousMultivariateDistribution {

    /**
     * Gives the mean, expected value of the ith variate in the
     * distribution.
     * @param index
     * @return
     */
    double expected(int index);

    /**
     * Variance of the distribution.
     * @return variance
     */
    double variance(int index);

    /**
     * Gives the <i>probability density function </i> value for the given argument, for this
     * probability distribution.
     * @param x
     * @return the value of the <i>pmf</i> for the given srguemnt, x.
     */
    double pdf(double[] xs);

    /**
     * Returns the entropy of the distribution.
     * @return
     */
    double entropy();
}
