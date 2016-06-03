import combinatorics.StirlingNumbers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StirlingNumbersTest {

    @Test
    public void test1(){
        long sn = StirlingNumbers.generateFirstKind(6, 3);
        assertEquals(-225,sn);
    }

    @Test
    public void test2(){
        long sn = StirlingNumbers.generateFirstKind(5, 2);
        assertEquals(-50,sn);
    }

    @Test
    public void test3(){
        long sn = StirlingNumbers.generateFirstKind(1, 1);
        assertEquals(1,sn);
    }

    @Test
    public void test4(){
        long sn = StirlingNumbers.generateFirstKind(10, 8);
        assertEquals(870,sn);
    }
}
