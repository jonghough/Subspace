import arithmetic.EulerNumber;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigInteger;

public class EulerNumberTest {

    @Test
    public void test1(){

        BigInteger s = EulerNumber.en(2);
        assertEquals("E(2)",s, new BigInteger("-1"));

        s = EulerNumber.en(4);
        assertEquals("E(4)",s, new BigInteger("5"));

         s = EulerNumber.en(6);
        assertEquals("E(6)",s, new BigInteger("-61"));

         s = EulerNumber.en(8);
        assertEquals("E(8)",s, new BigInteger("1385"));

        s = EulerNumber.en(10);
        assertEquals("E(10)",s, new BigInteger("-50521"));

        s = EulerNumber.en(12);
        assertEquals("E(12)",s, new BigInteger("2702765"));

        s = EulerNumber.en(14);
        assertEquals("E(14)",s, new BigInteger("-199360981"));

    }
}
