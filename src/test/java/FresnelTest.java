import complex.Cpx;
import org.junit.Test;
import transcendence.Exp;
import transcendence.Fresnel;

import static org.junit.Assert.*;

public class FresnelTest {

    @Test
    public void test1(){

        Cpx f = Fresnel.fresnel(0.0);
        assertTrue("C(0) == 0", f.real() == 0.0);
        assertTrue("S(0) == 0", f.imaginary() == 0.0);
    }

    @Test
    public void test2(){

        Cpx f = Fresnel.fresnel(Math.PI);
        System.out.println("frezen "+f.toString());
        assertTrue("C(PI) == 0.52369854381" , Math.abs(f.real() - 0.52369854381) < 0.00001);
        assertTrue("S(PI) == 0.59824907807", Math.abs(f.imaginary() - 0.59824907807) < 0.00001);
    }



}
