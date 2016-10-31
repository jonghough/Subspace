import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import transcendence.Zeta;

public class ZetaTest {

    @Test
    public void test1(){
        double a = Zeta.zetaMN(2);
        //should be B_{3} / 3
        assertTrue("Zeta (-2) is 0", a == 0);

        double b = Zeta.zetaMN(3);
        //4th bernoulli is -1/30
        assertTrue("Zeta(-3) is -1 / (30 * 4)", b == (-1.0 /(30 * 4)));
    }
}
