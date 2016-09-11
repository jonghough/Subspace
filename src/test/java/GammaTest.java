import org.junit.Test;
import transcendence.Gamma;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GammaTest {

    @Test
    public void test1(){
        BigDecimal d = Gamma.gamma(new BigDecimal("17.721"));
        assertTrue(d.subtract(new BigDecimal("160404007485874")).compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test2(){
        BigDecimal d = Gamma.gamma(new BigDecimal(0.5));
        assertTrue(d.subtract(new BigDecimal(Math.sqrt(Math.PI))).compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test3(){
        BigDecimal d = Gamma.gamma(new BigDecimal(10.0));
        assertTrue(d.subtract(new BigDecimal("362880")).compareTo(new BigDecimal("0.0001")) < 0);

        BigDecimal g = Gamma.incompleteGamma(new BigDecimal("1"),new BigDecimal("3"));
        assertTrue("incomplete gamma(1,3) is exp(-3) ~ 0.049787",
                g.subtract(new BigDecimal("0.049787")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }


}
