import org.junit.Test;
import transcendence.Exp;
import utils.RootFinder;

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

    @Test
    public void test4(){

        BigDecimal d1 = Exp.exp(new BigDecimal("0.45"));

        assertTrue((d1.subtract(new BigDecimal("1.56831")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }

    @Test
    public void test5(){

        BigDecimal d1 = Exp.exp(new BigDecimal("-10"));

        assertTrue((d1.subtract(new BigDecimal("4.53999E-5")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }

    @Test
    public void test6(){

        // e^ (163^0.5 * pi) ~ 262_537_412_640_768_744
        BigDecimal d1 = Exp.exp(RootFinder.squareRoot(new BigDecimal(163), 100).multiply(Exp.PI_PRECISE));
        assertTrue((d1.subtract(new BigDecimal("262537412640768744")).abs().compareTo(new BigDecimal(0.00001)) < 0));

    }
}
