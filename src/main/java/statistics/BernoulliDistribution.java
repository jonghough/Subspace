package statistics;
import java.util.ArrayList;


/**
 * Special case of <i>Binomial Distribution</i>
 */
public final class BernoulliDistribution extends BinomialDistribution{


    public BernoulliDistribution(double p) {
        super(1, p);
    }
}
