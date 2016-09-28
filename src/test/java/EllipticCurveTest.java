import ellipticcurves.BigEllipticCurve;
import ellipticcurves.EllipticCurve;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;

import static org.junit.Assert.*;


public class EllipticCurveTest {

    @Test
    public void test1(){
        BigInteger N = new BigInteger("17");
        BigInteger x= new BigInteger("5");
        BigInteger y = new BigInteger("1");
        BigInteger a = new BigInteger("2");
        BigInteger b = y.multiply(y).subtract(x.pow(3)).subtract(a.multiply(x)).mod(N);
        BigEllipticCurve bec = new BigEllipticCurve(a, b, N);
        BigEllipticCurve.Point p = bec.new Point(x, y, false);
        BigEllipticCurve.Point c = bec.add(p, p);
        c = bec.add(p,c);
        BigEllipticCurve.Point q = bec.mul(2, p);
        assertEquals(q.x,new BigInteger("6"));
        assertEquals(q.y,new BigInteger("3"));
        BigEllipticCurve.Point q2 = bec.mul(3, p);
        assertEquals(q2.x,new BigInteger("10"));
        assertEquals(q2.y,new BigInteger("6"));
        BigEllipticCurve.Point q3 = bec.mul(4, p);
        assertEquals(q3.x,new BigInteger("3"));
        assertEquals(q3.y,new BigInteger("1"));

    }

    @Test
    public void test2(){
        int N = 17;
        int x= 5;
        int y = 1;
        int a = 2;
        int b =  (y * y - (x*x*x) - a*x )% N;
        EllipticCurve ec = new EllipticCurve(a, b, N);
        EllipticCurve.Point p = ec.new Point(x, y, false);

        EllipticCurve.Point c = ec.add(p, ec.new Point(x, y, false));
        c = ec.add(c,p);

        assertEquals(c.x,10);
        assertEquals(c.y,6);

        ec.enumerateGroup();
        HashSet<EllipticCurve.Point> pts = ec.getAllPoints();


        assertEquals(pts.size(),18);
    }
}
