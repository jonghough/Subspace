import org.junit.Test;
import statistics.HypergeometricDistribution;

import static org.junit.Assert.*;

public class HypergeometricDistributionTest {

    @Test
    public void test1(){
        HypergeometricDistribution gd = new HypergeometricDistribution(10, 5, 5);
        double p = gd.pmf(3);
        assertTrue("X~Hyper(10,5,5), P(X = 3) = 0.396825", Math.abs(p - 0.396825) < 0.0001);
    }

    @Test
    public void test2(){
        HypergeometricDistribution gd = new HypergeometricDistribution(100, 20, 50);
        double p = gd.pmf(10);
        assertTrue("X~Hyper(100,20,50), P(X = 10) = 0.196871", Math.abs(p - 0.196871) < 0.0001);
    }

}
