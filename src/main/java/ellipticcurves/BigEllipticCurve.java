package ellipticcurves;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import primes.BSmooth;
import transcendance.Gamma;
import ellipticcurves.EllipticCurve.Point;
import factoring.Factorizor;
import factoring.PollardRho;
import arithmetic.Totient;

/**
 * Class for dealing with Elliptic Curves using BigIntegers rather than 32bit
 * ints.
 */
public class BigEllipticCurve {

    private BigInteger A, B, N;
    private static final BigInteger MINUS_ONE = new BigInteger("-1");
    private static final BigInteger TWO = new BigInteger("2");

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
        else if (a.x == b.x && a.y == MINUS_ONE.multiply(b.y)) {
            return new Point(BigInteger.ZERO, BigInteger.ZERO, false);
        } else {
            BigInteger lambda;
            // BigInteger phi = Totient.totient(N);
            if (a.x == b.x && a.y == b.y) {
                BigInteger denom = m(TWO.multiply(a.y));
                // inverse of the denominator modulo N

                BigInteger denomInv = null;
                try {
                    denomInv = denom.modInverse(N);
                } catch (ArithmeticException e) {
                    throw e;
                }
                lambda = m((TWO.multiply(a.x).multiply(a.x).add(A)).multiply(denomInv));
            } else {
                lambda = m(b.y.subtract(a.y));
                BigInteger inv = null;
                try {
                    inv = b.y.modInverse(N);
                } catch (ArithmeticException e) {
                    throw e;
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
     * Double and add algorithm to find the value of n*p, where p is a point on
     * the elliptic curve.
     *
     * @param p
     * @param n
     * @return
     */
    public Point doubleAndAdd(Point p, long n) {
        Point q = p;
        Point r = new Point(BigInteger.ZERO, BigInteger.ZERO, true);
        while (n >= 1) {
            try {
                if (n % 2 == 1) {
                    r = add(r, q);
                }

                q = add(q, q);
            } catch (ArithmeticException e) {
                // arithmetic exception implies we cannot find an
                // inverse modulo N, hence some value is not coprime to N,
                // and we can find a factor of N.
                return r;
            }
            n = (long) Math.floor(0.5 * n);
        }
        return r; // r = n*P
    }

    private static Point addInc(BigEllipticCurve curve, Point p, Point current) {
        Point r = null;
        try {
            r = curve.add(current, p);
        } catch (ArithmeticException e) {
            return r;
        }
        return r;
    }

    // work in progress...
    public static BigInteger factorLenstra(BigInteger N) {
        // choose random values
        final Random random = new Random(System.currentTimeMillis());
        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger y = new BigInteger(N.bitLength(), random);
        BigInteger a = new BigInteger(N.bitLength(), random);
        // calculate b
        BigInteger b = y.multiply(y).subtract(x.pow(3)).subtract(a.multiply(x)).mod(N);

        // assume elliptic curve y^2 = x^3 +ax+b
        // set bound
        long bound = BSmooth.L(N).toBigInteger().longValue();
        //prevent bound being too small.
        if (bound < 100) bound = 100;

        //	bound = 6469693230L;
        BigEllipticCurve bec = new BigEllipticCurve(a, b, N);
        Point p = bec.new Point(x, y, false);
        Point q = null;
        long counter = 2;
        System.out.println("EC bounds is " + bound);
        while (counter++ < bound) {
            if (q == null)
                q = bec.doubleAndAdd(p, counter);
            else {
                q = addInc(bec, p, q);
            }
            BigInteger k = q.y.gcd(N);
            BigInteger l = q.x.gcd(N);

            if (l.compareTo(BigInteger.ONE) > 0 && q.x != N) {

                if (l.isProbablePrime(99)) {
                    System.out.println("is prime " + l.toString());
                    return l;
                } else {
                    System.out.println("not prime, factor " + l.toString());
                    return factorLenstra(l);
                }
            }
        }
        System.out.println("exhaust bound " + N.toString());
        if (N.isProbablePrime(99))
            return N;
        else return PollardRho.pollardRho(N);
    }

}
