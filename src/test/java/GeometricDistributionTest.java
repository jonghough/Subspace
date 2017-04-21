import org.junit.Test;
import statistics.GeometricDistribution;

import static org.junit.Assert.*;

public class GeometricDistributionTest {

    @Test
    public void test1(){
        GeometricDistribution gd = new GeometricDistribution(0.1);
        double p = gd.pmf(10);
        assertTrue("X~Geo(0.1), P(X = 10) = 0.038742", Math.abs(p - 0.038742) < 0.0001);
    }

    @Test
    public void test2(){
        GeometricDistribution gd = new GeometricDistribution(0.33);
        double p = gd.pmf(14);
        assertTrue("X~Geo(0.33), P(X = 14) = 0.001809199", Math.abs(p - 0.00180920) < 0.0001);
    }


    @Test(expected=IllegalArgumentException.class)
    public void test3(){
        // Should throw an exception.
        GeometricDistribution gd = new GeometricDistribution(0.33);
        double p = gd.pmf(-1);
    }
}
