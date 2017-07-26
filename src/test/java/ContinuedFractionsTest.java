
import arithmetic.ContinuedFractions;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ContinuedFractionsTest {


    @Test
    public void test1() {
        ArrayList<Integer> i = ContinuedFractions.sqrtToCF(114, 100);
        for (Integer x : i) {
            System.out.println("x= " + x);
        }
    }

    @Test
    public void test2() {
        System.out.println("350");
        ArrayList<Integer> i = ContinuedFractions.sqrtToCF(350000, 100);
        for (Integer x : i) {
            System.out.println("x= " + x);
        }
    }

    @Test
    public void test3() {
        ArrayList<Integer> i = ContinuedFractions.realToCF(Math.PI, 10);
        assertTrue(i.get(0) == 3);
        assertTrue(i.get(1) == 7);
        assertTrue(i.get(2) == 15);
        assertTrue(i.get(3) == 1);
        assertTrue(i.get(4) == 292);
        assertTrue(i.get(5) == 1);
        assertTrue(i.get(6) == 1);
        assertTrue(i.get(7) == 1);
        assertTrue(i.get(8) == 2);
        assertTrue(i.get(9) == 1);
    }

    @Test
    public void test4() {
        ArrayList<Integer> i = ContinuedFractions.realToCF((1.0 + Math.sqrt(5))/2.0, 10);
        assertTrue(i.get(0) == 1);
        assertTrue(i.get(1) == 1);
        assertTrue(i.get(2) == 1);
        assertTrue(i.get(3) == 1);
        assertTrue(i.get(4) == 1);
        assertTrue(i.get(5) == 1);
        assertTrue(i.get(6) == 1);
        assertTrue(i.get(7) == 1);
        assertTrue(i.get(8) == 1);
        assertTrue(i.get(9) == 1);
    }
}
