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
}
