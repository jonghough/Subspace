import org.junit.Test;
import transcendence.Exp;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ExpTest {

    @Test
    public void test1(){

        BigDecimal d1 = Exp.exp(new BigDecimal("1.3"));

        assertTrue((d1.subtract(new BigDecimal("3.6692967")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }

    @Test
    public void test2(){

        BigDecimal d1 = Exp.exp(new BigDecimal("0"));

        assertTrue((d1.subtract(new BigDecimal("1")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }

    @Test
    public void test3(){

        BigDecimal d1 = Exp.exp(new BigDecimal("14.567"));

        assertTrue((d1.subtract(new BigDecimal("2120155.5148095")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }
}
