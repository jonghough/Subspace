import arithmetic.Totient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.math.BigInteger;

public class TotientTest {

    @Test
    public void test1(){
        BigInteger big = new BigInteger("35");
        BigInteger tot = Totient.totient(big);
        assertEquals(tot.toString(), "24");

    }
}
