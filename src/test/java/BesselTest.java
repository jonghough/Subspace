import org.junit.Test;
import transcendence.Bessel;
import static org.junit.Assert.*;

public class BesselTest {

    @Test
    public void test1(){
        double a = Bessel.besselJ0(12.783);
        assertTrue("BesselJ0(12.783)", Math.abs(a - 0.186778572) < 0.0001);
         a = Bessel.besselY0(12.783);
        assertTrue("BesselY0(12.783)", Math.abs(a - -0.12197587) < 0.0001);
    }

    @Test
    public void test2(){
        double a = Bessel.besselJ0(-10.5);
        assertTrue("BesselJ0(-10.5)", Math.abs(a - -0.23662819446) < 0.0001);
    }

    @Test
    public void test3(){
        double a = Bessel.besselJ0(20.0);
        assertTrue("BesselJ0(20.0)", Math.abs(a - 0.16702466434) < 0.0001);
        a = Bessel.besselY0(20.0);
        assertTrue("BesselY0(20.0)", Math.abs(a - 0.062605968094) < 0.0001);
    }

    @Test
    public void test4(){
        double a = Bessel.besselJ1(1.5);
        assertTrue("BesselJ1(1.5)", Math.abs(a - 0.557936508) < 0.0001);
        a = Bessel.besselY1(1.5);
        assertTrue("BesselY1(1.5)", Math.abs(a - -0.412308627) < 0.0001);
    }

    @Test
    public void test5(){
        double a = Bessel.besselJ1(6.752);
        assertTrue("BesselJ1(6.752)", Math.abs(a - -0.07972000489) < 0.0001);
        a = Bessel.besselY1(6.752);
        assertTrue("BesselY1(6.752)", Math.abs(a - -0.2978115455226) < 0.0001);
    }

    @Test
    public void test6(){
        double a = Bessel.besselJ1(3.0);
        assertTrue("BesselJ1(3)", Math.abs(a - 0.33905895853) < 0.0001);
        a = Bessel.besselY1(3.0);
        assertTrue("BesselY1(3)", Math.abs(a - 0.324674424792) < 0.0001);
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionTest1(){
        double a = Bessel.besselY0(-3.0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionTest2(){
        double a = Bessel.besselY0(0.0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionTest3(){
        double a = Bessel.besselY1(0.0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionTest5(){
        double a = Bessel.besselY(-2,10.0);
    }


    @Test(expected=IllegalArgumentException.class)
    public void exceptionTest4(){
        double a = Bessel.besselY(2,0.0);
    }

    @Test
    public void test7(){
        double a = Bessel.besselJ(3,3.0);
        assertTrue("BesselJ(3,3)", Math.abs(a - 0.30906272226) < 0.0001);
    }

    @Test
    public void test8(){
        double a = Bessel.besselY(3,3.0);
        assertTrue("BesselY(3,3)", Math.abs(a - -0.538541616105) < 0.0001);
    }

    @Test
    public void test9(){
        double a = Bessel.besselI(3,5.0);
       // a = Bessel.besselI0(5.0);
        System.out.println("a is    "+a);
        assertTrue("BesselI(3,5)", Math.abs(a - 10.331150169) < 0.0001);
    }
}
