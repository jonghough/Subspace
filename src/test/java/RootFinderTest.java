import org.junit.Test;
import utils.RootFinder;
import static org.junit.Assert.*;
import java.math.BigDecimal;

public class RootFinderTest {

    @Test
    public void test1(){
        BigDecimal root = RootFinder.cubeRoot(new BigDecimal("1500"), 20);
        assertTrue(root.subtract(new BigDecimal("11.4471")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test2(){
        BigDecimal root = RootFinder.cubeRoot(new BigDecimal("1954.201"), 20);
        assertTrue(root.subtract(new BigDecimal("12.5023")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test3(){
        BigDecimal root = RootFinder.squareRoot(new BigDecimal("49"), 20);
        assertTrue(root.subtract(new BigDecimal("7")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test4(){
        BigDecimal root = RootFinder.squareRoot(new BigDecimal("109450.5"), 20);
        assertTrue(root.subtract(new BigDecimal("330.833")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }

    @Test
    public void test5(){
        BigDecimal root = RootFinder.squareRoot(new BigDecimal("163"), 20);
        assertTrue(root.subtract(new BigDecimal("12.7671453348")).abs().compareTo(new BigDecimal("0.0001")) < 0);
    }
}
