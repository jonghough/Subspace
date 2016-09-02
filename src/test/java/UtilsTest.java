import org.junit.Test;
import utils.BigUtils;

import static org.junit.Assert.assertEquals;


public class UtilsTest {

    @Test
    public void test1(){
        int g = BigUtils.modInverse(5, 11);
        assertEquals("inverse of 5 mod 11 is 9, ",9,g);
    }

    @Test
    public void test2(){
        int g = BigUtils.modInverse(11, 17);
        assertEquals("inverse of 11 mod 17 is 14, ",14,g);
    }
}
