package statistics;


import numerics.IFunction;
import numerics.Integration;

/**
 *
 */
public class NormalDistribution implements ContinuousDistribution {

    private final double mMean;
    private final double mVariance;

    public NormalDistribution(double mean, double variance){
        mMean = mean;
        mVariance = variance;
    }


    @Override
    public double expected() {
        return mMean;
    }

    @Override
    public double variance() {
        return mVariance;
    }

    @Override
    public double skew() {
        return 0;
    }

    @Override
    public double kurtosis() {
        return 0;
    }

    @Override
    public double pdf(double x) {
        return Math.exp(-(x - mMean) * (x - mMean) / (2 * mVariance)) * 1.0 / (Math.sqrt(2 * Math.PI * mVariance));
    }

    @Override
    public double cdf(double k) {
        if(k < mMean - Math.sqrt(mVariance) * 10) return 0.0;
        else if(k > mMean + Math.sqrt(mVariance) * 10) return 1.0;
        else {

            return Integration.integrateAdaptiveQuadrature(new IFunction() {
                @Override
                public float evaluateAt(float x) {
                    return (float) pdf(x);
                }
            }, (float) (mMean - Math.sqrt(mVariance) * 10), (float) k);
        }
    }

    @Override
    public double entropy() {
        return 0.5 * Math.log(2 * variance() * Math.PI * Math.E);
    }
}
