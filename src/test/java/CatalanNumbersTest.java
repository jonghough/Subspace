import combinatorics.CatalanNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CatalanNumbersTest {

    @Test
    public void test1(){
        long l = new CatalanNumber(4).getCatalanNumber();
        assertEquals(14, l);
    }

    @Test
    public void test2(){
        long l = new CatalanNumber(5).getCatalanNumber();
        assertEquals(42,l);
    }

    @Test
     public void test3(){
        long l = new CatalanNumber(11).getCatalanNumber();
        assertEquals(58786, l);
    }

    @Test
    public void test5(){
        long l = new CatalanNumber(16).getCatalanNumber();
        assertEquals(35357670, l);
    }

    @Test
    public void test6(){
        // test rank
        long rank = new CatalanNumber(5).catalanRank(new boolean[]{false, false, true, false, true, true, false, true, false, true});
        assertEquals(22, rank);
    }

    @Test
    public void test7(){
        // test rank
        long rank = new CatalanNumber(4).catalanRank(new boolean[]{false, false, true, false, true, false, true, true});
        assertEquals(5, rank);
    }

    @Test
    public void test8(){
        // test rank
        long rank = new CatalanNumber(4).catalanRank(new boolean[]{false, false, false, false, true, true, true, true});
        assertEquals(0, rank);
    }

    @Test
    public void test9(){
        // test rank
        long rank = new CatalanNumber(4).catalanRank(new boolean[]{false, true, false, true, false, true, false, true});
        assertEquals(13, rank);
    }
}
