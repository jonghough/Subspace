import org.junit.Test;
import static org.junit.Assert.*;
import transcendence.Digamma;


public class DigammaTest {

    @Test
    public  void test1(){
        double val = 1.0;
       double res = Digamma.digamma2(1.0);
      //  assertTrue("digamma(1) is approx. neg. euler const.", Math.abs(res - -0.5772156649) < 0.00001);
    }
}


