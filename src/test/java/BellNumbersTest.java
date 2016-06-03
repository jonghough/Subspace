import static org.junit.Assert.*;

import arithmetic.BernoulliNumbers;
import arithmetic.Rational;
import combinatorics.BellNumbers;
import org.junit.Test;

import java.util.ArrayList;


public class BellNumbersTest {

    @Test
    public void test1() {
        long k = BellNumbers.generateBellNumber(1);
        assertEquals(k, 1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void test2() {
        long k = BellNumbers.generateBellNumber(-1);
    }

    @Test
    public void test3() {
        long k = BellNumbers.generateBellNumber(5);
        assertEquals(k, 52);
    }

    @Test
    public void test4() {
        long k = BellNumbers.generateBellNumber(10);
        assertEquals(k, 115975);
    }
}
