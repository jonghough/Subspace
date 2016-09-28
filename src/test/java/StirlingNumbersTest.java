import combinatorics.StirlingNumbers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StirlingNumbersTest {

    @Test
    public void test1(){
        long sn = StirlingNumbers.generateFirstKind(6, 3, true);
        assertEquals(-225,sn);
    }

    @Test
    public void test2(){
        long sn = StirlingNumbers.generateFirstKind(5, 2, true);
        assertEquals(-50,sn);
    }

    @Test
    public void test3(){
        long sn = StirlingNumbers.generateFirstKind(1, 1, false);
        assertEquals(1,sn);
    }

    @Test
    public void test4(){
        long sn = StirlingNumbers.generateFirstKind(10, 8, false);
        assertEquals(750,sn);
    }

    @Test
    public void test5(){
        long sn = StirlingNumbers.generateFirstKind(7, 1, false);
        assertEquals(1,sn);
    }

    @Test
    public void test6(){
        long sn = StirlingNumbers.generateFirstKind(7, 7, false);
        assertEquals(1,sn);
    }

    @Test
    public void test7(){
        long sn = StirlingNumbers.generateFirstKind(25, 20, false);
        assertEquals(6220194750L,sn);
    }

    @Test
    public void test8(){
        long sn = StirlingNumbers.generateFirstKind(6, 4, true);
        assertEquals(85,sn);
    }

    @Test
    public void test9(){
        long sn = StirlingNumbers.generateFirstKind(6, 5, true);
        assertEquals(-15,sn);
    }

    @Test
    public void test10(){
        long sn = StirlingNumbers.generateFirstKind(6, 1, true);
        assertEquals(-120,sn);
    }

    @Test
    public void test11(){
        long sn = StirlingNumbers.generateFirstKind(17, 15, true);
        assertEquals(8500,sn);
    }
}
