import org.junit.Test;
import statistics.BinomialDistribution;
import static org.junit.Assert.*;

public class BinomialDistributionTest {

    @Test
    public void test1(){
        BinomialDistribution bd = new BinomialDistribution(10,0.4);
        double p = bd.pmf(5);
        assertTrue("X~Bin(10, 0.4), P(X = 5) = 0.200658", Math.abs(p - 0.200658) < 0.0001);
    }

    @Test
    public void test2(){
        BinomialDistribution bd = new BinomialDistribution(100,0.63);
        double p = bd.pmf(56);
        assertTrue("X~Bin(100, 0.63), P(X = 56) = 0.0286736", Math.abs(p - 0.0286736) < 0.0001);
    }
}
