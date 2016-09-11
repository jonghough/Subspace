import ellipticcurves.BigEllipticCurve;
import ellipticcurves.EllipticCurve;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashSet;


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
        System.out.println(" ana d b "+a+", "+b+", p = "+p.x +", "+p.y);
        c = bec.add(p,c);
        System.out.println("t1 P + p + p = "+c.x+", "+c.y);
        BigEllipticCurve.Point q = bec.mul(2, p);
        System.out.println(" Elliptic 2*p = "+q.x+", "+q.y);
        BigEllipticCurve.Point q2 = bec.mul(3, p);
        System.out.println(" Elliptic 3*mul = "+q2.x+", "+q2.y);
        BigEllipticCurve.Point q3 = bec.mul(4, p);
        System.out.println(" Elliptic 3*mul = "+q3.x+", "+q3.y);


    }

    @Test
    public void test2(){
        int N = 17;
        int x= 5;
        int y = 1;
        int a = 2;
        int b =  (y * y - (x*x*x) - a*x )% N;//y.multiply(y).subtract(x.pow(3)).subtract(a.multiply(x)).mod(N);
        EllipticCurve ec = new EllipticCurve(a, b, N);
        EllipticCurve.Point p = ec.new Point(x, y, false);
        System.out.println(" a and b "+a+", "+b+", p = "+p.x +", "+p.y);

        EllipticCurve.Point c = ec.add(p, ec.new Point(x, y, false));
        c = ec.add(c,p);
        System.out.println("t2:  p + p + p = "+c.x+", "+c.y);
       // BigEllipticCurve.Point q = ec.mul(2, p);
        //System.out.println("t2 Elliptic mul = "+q.x+", "+q.y);

        ec.enumerateGroup();
        HashSet<EllipticCurve.Point> pts = ec.getAllPoints();
        for(EllipticCurve.Point pt : pts){
            System.out.println("point: "+pt.x +" ,  "+pt.y);
        }
    }
}
