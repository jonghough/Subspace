import org.junit.Test;
import primes.Primorial;

import java.math.BigInteger;

import static org.junit.Assert.*;


public class PrimorialTest {

    @Test
    public void test1(){
        assertEquals("Primorial(2) = 2", new BigInteger("2"), Primorial.primorial(1));
    }

    @Test
    public void test2(){
        assertEquals("Primorial(5) = 2310",new BigInteger("2310"), Primorial.primorial(5));
    }

    @Test
    public void test3(){
        assertEquals("Primorial(6) = 30_030",new BigInteger("30030"), Primorial.primorial(6));
    }
}
