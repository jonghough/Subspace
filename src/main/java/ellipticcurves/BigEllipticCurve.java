package ellipticcurves;

import java.math.BigInteger;
import java.util.Random;

import arithmetic.Totient;
import primes.BSmooth;
import factoring.PollardRho;
import utils.RootFinder;

/**
 * Class for dealing with Elliptic Curves using BigIntegers rather than 32bit
 * ints.
 */
public class BigEllipticCurve {

    private BigInteger A, B, N;
    private static final BigInteger MINUS_ONE = new BigInteger("-1");
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");

    public BigEllipticCurve(BigInteger A, BigInteger B, BigInteger N) {
        this.A = A;
        this.B = B;
        this.N = N;
    }

    public class Point {
        public BigInteger x;
        public BigInteger y;
        public boolean IsInfinite;

        public Point(BigInteger u, BigInteger v, boolean isInfinite) {
            x = u;
            y = v;
            IsInfinite = isInfinite;
        }

        public Point(Point p) {
            x = p.x;
            y = p.y;
            IsInfinite = p.IsInfinite;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + (IsInfinite ? 1231 : 1237);
            result = prime * result + ((x == null) ? 0 : x.hashCode());
            result = prime * result + ((y == null) ? 0 : y.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (IsInfinite != other.IsInfinite)
                return false;
            if (x == null) {
                if (other.x != null)
                    return false;
            } else if (!x.equals(other.x))
                return false;
            if (y == null) {
                if (other.y != null)
                    return false;
            } else if (!y.equals(other.y))
                return false;
            return true;
        }

        @Override
        public String toString(){
            return "("+x.toString()+", "+y.toString()+")";
        }

        /**
         * Negates the point (negates the y-coordinate.
         * @return Negation of this point.
         */
        public Point negate(){
            return new Point(x, y.multiply(MINUS_ONE), IsInfinite);
        }

        /**
         * Copies this point.
         * @return New point with coordinates equal to this point.
         */
        public Point copy(){
            return new Point(x, y, IsInfinite);
        }

        private BigEllipticCurve getOuterType() {
            return BigEllipticCurve.this;
        }

    }

    private static BigInteger powerMod(BigInteger a, BigInteger r, BigInteger M) {
        return a.modPow(r, M);
    }

    /**
     * reutnr i Modulo N
     *
     * @param i
     * @return
     */
    private BigInteger m(BigInteger i) {
        return (i.add(N)).mod(N);
    }

    /**
     * Adds two points on the instance's elliptic curve (curve defined by A, B,
     * N params of the constructor).
     *
     * @param a
     * @param b
     * @return the sum a+b on the curve, will be a third point on the curve.
     * @throws ArithmeticException
     */
    public Point add(Point a, Point b) {
        if (a.IsInfinite)
            return new Point(b);
        else if (b.IsInfinite)
            return new Point(a);
        else if (a.x.compareTo(b.x) == 0 && a.y.compareTo(MINUS_ONE.multiply(b.y)) == 0) {
            return new Point(BigInteger.ZERO, BigInteger.ZERO, false);
        } else {
            BigInteger lambda;

            if (a.x.compareTo(b.x) == 0 && a.y.compareTo(b.y) == 0) {
                BigInteger denom = m(TWO.multiply(a.y));
                // inverse of the denominator modulo N

                BigInteger denomInv;
                try {
                    denomInv = denom.modInverse(N);
                } catch (ArithmeticException e) {
                    throw new EllipticCurveException(e.getMessage(), m(denom));
                }
                lambda = m((THREE.multiply(a.x).multiply(a.x).add(A)).multiply(denomInv));
            } else {
                lambda = m(b.y.subtract(a.y));
                //check if the two points add to infinity (zero)
                if(m(b.x.subtract(a.x)).compareTo(BigInteger.ZERO) == 0)
                    return new Point(BigInteger.ZERO, BigInteger.ZERO, true);

                BigInteger inv;
                try {
                    inv = b.x.subtract(a.x).modInverse(N);
                } catch (ArithmeticException e) {
                    throw new EllipticCurveException(e.getMessage(), m(b.x.subtract(a.x)));
                }
                inv = m(inv);
                lambda = lambda.multiply(inv);
            }
            lambda = m(lambda);
            BigInteger newx = m(lambda.multiply(lambda).subtract(a.x).subtract(b.x));
            if (newx.compareTo(BigInteger.ZERO) < 0)
                newx = m(newx);
            BigInteger newy = m(MINUS_ONE.multiply(lambda).multiply(newx).subtract(a.y.subtract(lambda.multiply(a.x))));

            if (newy.compareTo(BigInteger.ZERO) < 0)
                newy = m(newy);
            return new Point(newx, newy, false);
        }
    }


    /**
     *
     * @param d
     * @param p
     * @return
     */
    public Point mul(int d, Point p){
        Point q = new Point(BigInteger.ZERO, BigInteger.ZERO, true);
        Point r = p.copy();
        BigInteger D = new BigInteger(String.valueOf(d));
        for(int i = 0; i < D.bitLength(); i ++){
            if(D.shiftRight(i).and(BigInteger.ONE).compareTo(BigInteger.ONE) == 0){

                //may throw an exception -- possible factor found.
                Point s;
                s = this.add(q, r);
                q = s;
            }
            r = this.add(r, r);
        }
        return q;
    }


    /**
     *
     * @param N
     * @return
     */
    public static BigInteger factorLenstra(BigInteger N) {
        if(N.isProbablePrime(100))
            return N;

        if (N.mod(TWO).compareTo(BigInteger.ZERO) == 0)
            return TWO;

        if (N.mod(THREE).compareTo(BigInteger.ZERO) == 0)
            return THREE;
        // choose random values
        final Random random = new Random(System.currentTimeMillis());
        int k = 100;
        while (k-- > 0) {
            BigInteger x = new BigInteger(18, random).add(BigInteger.ONE).mod(N);
            BigInteger y = new BigInteger(18, random).add(BigInteger.ONE).mod(N);
            BigInteger a = new BigInteger(18, random).add(BigInteger.ONE).mod(N);

            // calculate b
            BigInteger b = y.multiply(y).subtract(x.pow(3)).subtract(a.multiply(x)).mod(N);

            // assume elliptic curve y^2 = x^3 +ax+b
            // set bound
            long bound = BSmooth.L(N).toBigInteger().longValue();
            //prevent bound being too small.
            if (bound < 100) bound = 100;


            BigEllipticCurve bec = new BigEllipticCurve(a, b, N);
            Point p = bec.new Point(x, y, false);
            Point q = null;
            long counter = 2;
            while (counter++ < bound) {

                if(p.IsInfinite) break;
                try{
                    q = bec.mul((int)counter, p);

                }catch(EllipticCurveException e){
                    if(e.exceptionPoint.compareTo(BigInteger.ZERO) != 0) {
                        BigInteger l = e.exceptionPoint.gcd(N);
                        if (l.compareTo(BigInteger.ONE) > 0 && l.compareTo(N) < 0) {

                            if (l.isProbablePrime(99)) {
                                System.out.println("ECF found prime factor " + l.toString());
                                return l;
                            } else {
                                return factorLenstra(l);
                            }
                        }
                    }

                }
                p = q;
            }
        }
        System.out.println("ECF exhausted bounds " + N.toString());
        if (N.isProbablePrime(99))
            return N;
        else return PollardRho.pollardRho(N);

    }

}
