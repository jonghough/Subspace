import combinatorics.Binomial;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinomialTest {

    @Test
    public void test1(){
        long b = Binomial.coefficient(5,2);
        assertEquals(b, 10);

        long b2 = Binomial.coefficient(10,6);
        assertEquals(b2, 210);

        long b3 = Binomial.coefficient(15,7);
        assertEquals(b3, 6435);


        long b4 = Binomial.coefficient(10,10);
        assertEquals(b4, 1);


        long b5 = Binomial.coefficient(12,1);
        assertEquals(b5, 12);
    }
}
