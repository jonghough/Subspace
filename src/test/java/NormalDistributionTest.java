import org.junit.Test;
import statistics.NormalDistribution;

import static org.junit.Assert.assertTrue;

public class NormalDistributionTest {
    @Test
    public void test1(){
        NormalDistribution nd = new NormalDistribution(0,1);
        double p = nd.cdf(0);

        assertTrue("X~Norm(0,1), P(X < 0) = 0.5", Math.abs(p - 0.5) < 0.0001);
    }

    @Test
    public void test2(){
        NormalDistribution nd = new NormalDistribution(22.4,10.6);
        double p = nd.cdf(24.5);
        assertTrue("X~Norm(22.4,10.6), P(X < 24.5) = 0.74055058", Math.abs(p - 0.74055058) < 0.0001);
    }
}
