
import arithmetic.BernoulliNumbers;
import arithmetic.Rational;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BernoulliNumbersTest {

    @Test
    public void test1() {
        ArrayList<Rational> bNumbers = BernoulliNumbers.generateBernoulliNumbers(5);
        assertEquals(bNumbers.get(0).getNumerator() , 1);
        assertEquals(bNumbers.get(1).getNumerator() , 1);
        assertEquals(bNumbers.get(1).getDenominator() , 6);
        assertEquals(bNumbers.get(2).getNumerator() , -1);
        assertEquals(bNumbers.get(2).getDenominator() , 30);
        assertEquals(bNumbers.get(3).getNumerator() , 1);
        assertEquals(bNumbers.get(3).getDenominator() , 42);
    }
}
