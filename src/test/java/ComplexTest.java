import complex.Cpx;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexTest {

    @Test
    public void test1(){
        Cpx a = new Cpx(1,1);
        Cpx b = new Cpx(1,-1);

        Cpx c = a.add(b);
        assertTrue(c.real() == 2);
        assertTrue(c.imaginary() == 0);
    }

    @Test
    public void test2(){
        Cpx a = new Cpx(1,1);
        Cpx b = new Cpx(1,-1);

        Cpx c = a.multiply(b);
        assertTrue(c.real() == (1*1 - (-1*1)));
        assertTrue(c.imaginary() == 0);
    }

    @Test
    public void test3(){
        Cpx a = new Cpx(3.14,2.71);
        Cpx b = new Cpx(1.618, 0.5);

        Cpx c = a.multiply(b);
        assertTrue(c.real() == 3.14*1.618 - 2.71*0.5);
        assertTrue(c.imaginary() == 3.14 * 0.5 + 2.71 * 1.618);
    }

    @Test
    public void test4(){
        Cpx a = new Cpx(2,5);
        Cpx b = a.inverse();

        assertTrue(b.real() == 2.0 / 29.0);
        assertTrue(b.imaginary() == -5.0 / 29.0);
    }

    @Test
    public void test5(){
        Cpx a = new Cpx(3,5);
        Cpx b = new Cpx(0.2, 1.5);

        Cpx c = a.pow(b);
        assertTrue(Math.abs(c.real() - -0.29060063574027406) < 0.00001);
        assertTrue(Math.abs(c.imaginary() - 0.08695537451613247) < 0.00001);
    }
}
