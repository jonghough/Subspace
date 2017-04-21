package statistics;

import transcendence.Gamma;

import java.math.BigDecimal;

public class DirichletDistribution implements ContinuousMultivariateDistribution {

    private int K;
    private float[] Params;
    private float paramSum;

    public DirichletDistribution(int k, float[] params) {
        if (k < 2) throw new IllegalArgumentException("Parameter k must not be less than 2.");
        K = k;
        if (params.length == 0)
            throw new IllegalArgumentException("Params array must not be empty.");
        Params = new float[params.length];
        for (int i = 0; i < params.length; i++) {
            float f = params[i];
            if (f < 0)
                throw new IllegalArgumentException("Parameter must be positivie, value is " + f);
            Params[i] = f;
            paramSum += f;
        }
    }

    @Override
    public double expected(int index) {
        return Params[index] / paramSum;
    }

    @Override
    public double variance(int index) {
        return Params[index] * (paramSum - Params[index]) / (paramSum * paramSum * (paramSum + 1));
    }

    @Override
    public double pdf(double[] xs) {
        if (xs.length != Params.length)
            throw new IllegalArgumentException("Param lengths are not equal.");
        float d = 0;
        for (int i = 0; i < Params.length; i++) {
            d *= Math.pow(xs[i], Params[i] - 1);
        }
        return d / mbf();
    }

    @Override
    public double entropy() {
        return 0;
    }


    private float mbf() {
        double num = 0; //numerator
        double den = 1; //denominator
        for (int i = 0; i < Params.length; i++) {
            double g = Gamma.gamma(new BigDecimal(Params[i])).doubleValue();
            num += g;
            den *= g;
        }

        return (float) (num / den);
    }
}
