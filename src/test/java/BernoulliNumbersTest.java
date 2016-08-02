
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

    @Test
     public void test2(){
        Rational b10 = BernoulliNumbers.calculateNthBernoulliNumber(10);
        assertEquals(5, b10.getNumerator());
        assertEquals(66, b10.getDenominator());
    }

    @Test
    public void test3(){
        Rational b16 = BernoulliNumbers.calculateNthBernoulliNumber(16);
        assertEquals(-3617, b16.getNumerator());
        assertEquals(510, b16.getDenominator());
    }

    @Test
    public void test4(){
        Rational b22 = BernoulliNumbers.calculateNthBernoulliNumber(22);
        assertEquals(854513, b22.getNumerator());
        assertEquals(138, b22.getDenominator());
    }
}
