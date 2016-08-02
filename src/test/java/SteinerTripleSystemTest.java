import combinatorics.SteinerTripleSystem;
import static org.junit.Assert.*;
import org.junit.Test;
import transcendance.Exp;

import java.math.BigDecimal;

public class SteinerTripleSystemTest {

    @Test
    public void test1(){
        SteinerTripleSystem sts7 = new SteinerTripleSystem(7);
        assertTrue("The number of blocks should be 7, for STS(7)", sts7.getElements().size() == 7);
    }

    @Test
    public void test2(){
        SteinerTripleSystem sts9 = new SteinerTripleSystem(9);
        assertTrue("The number of blocks should be 12, for STS(9)", sts9.getElements().size() == 12);
    }
}
