import org.junit.Test;
import transcendance.BigTrigonometry;
import transcendance.Exp;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ExpTest {

    @Test
    public void test1(){

        BigDecimal d1 = Exp.exp(new BigDecimal("1.3"));

        assertTrue((d1.subtract(new BigDecimal("3.6692967")).abs().compareTo(new BigDecimal(0.001)) < 0));

    }
}
