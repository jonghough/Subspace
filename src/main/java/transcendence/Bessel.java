package transcendence;


/**
 * Class contains static methods for computing <i>Bessel Functions</i> of the
 * <i>First Kind</i> and <i>Second Kind</i>.
 * <br>
 * Source for the algorithms: <i>Numerical Recipes</i>
 */
public class Bessel {

    /**
     * Calculates <i>Bessel Function of the First Kind</i>, <i>J_{0}(x)</i>, for real value x.
     * <br>
     * Algorithm from <i>Numerical Recipes, p.232</i>.
     *
     * @param x double value
     * @return bessel function approximation for the given value.
     */
    public static double besselJ0(double x) {
        double y;
        double a0, a1, a2;
        double ax = Math.abs(x);
        if (ax < 8.0) {
            y = x * x;
            a1 = 57568490574.0 + y * (-13362590354.0 + y * (651619640.7 + y * (-11214424.18 + y * (77392.33017 + y * (-184.9052456)))));
            a2 = 57568490411.0 + y * (1029532985.0 + y * (9494680.718 + y * (59272.64853 + y * (267.8532712 + y * 1.0))));
            a0 = a1 / a2;
        } else {
            double z = 8.0 / ax;
            y = z * z;
            double d = ax - 0.785398164;
            a1 = 1.0 + y * (-0.1098628627e-2 + y * (0.2734510407e-4
                    + y * (-0.2073370639e-5 + y * 0.2093887211e-6)));
            a2 = -0.1562499995e-1 + y * (0.1430488765e-3
                    + y * (-0.6911147651e-5 + y * (0.7621095161e-6
                    - y * 0.934945152e-7)));
            a0 = Math.sqrt(0.636619772 / ax) * (Math.cos(d) * a1 - z * Math.sin(d) * a2);
        }
        return a0;
    }


    /**
     * Calculates <i>Bessel Function of the First Kind</i>, <i>J_{1}(x)</i>, for real value x.
     * <br>
     * Algorithm from <i>Numerical Recipes, p.233</i>.
     *
     * @param x double value
     * @return bessel function approximation for the given value.
     */
    public static double besselJ1(double x) {
        double y;
        double a0, a1, a2;
        double ax = Math.abs(x);
        if (ax < 8.0) {
            y = x * x;
            a1 = x * (72362614232.0 + y * (-7895059235.0 + y * (242396853.1
                    + y * (-2972611.439 + y * (15704.48260 + y * (-30.16036606))))));
            a2 = 144725228442.0 + y * (2300535178.0 + y * (18583304.74
                    + y * (99447.43394 + y * (376.9991397 + y * 1.0))));
            a0 = a1 / a2;
        } else {
            double z = 8.0 / ax;
            y = z * z;
            double d = ax - 2.356194491;
            a1 = 1.0 + y * (0.183105e-2 + y * (-0.3516396496e-4
                    + y * (0.2457520174e-5 + y * (-0.240337019e-6))));
            a2 = 0.04687499995 + y * (-0.2002690873e-3
                    + y * (0.8449199096e-5 + y * (-0.88228987e-6
                    + y * 0.105787412e-6)));
            a0 = Math.sqrt(0.636619772 / ax) * (Math.cos(d) * a1 - z * Math.sin(d) * a2);
            if (x < 0.0) a0 = -a0;
        }
        return a0;
    }


    /**
     * Calculates <i>Bessel Function of the Second Kind</i>, <i>Y_{0}(x)</i>, for real value x.
     * <br>
     * Algorithm from <i>Numerical Recipes, p.232</i>.
     *
     * @param x double value
     * @return
     */
    public static double besselY0(double x) {
        if (x <= 0) throw new IllegalArgumentException("Argument must be positive.");
        double a0, a1, a2, y;
        if (x < 8.0) {
            y = x * x;
            a1 = -2957821389.0 + y * (7062834065.0 + y * (-512359803.6
                    + y * (10879881.29 + y * (-86327.92757 + y * 228.4622733))));
            a2 = 40076544269.0 + y * (745249964.8 + y * (7189466.438
                    + y * (47447.26470 + y * (226.1030244 + y * 1.0))));
            a0 = (a1 / a2) + 0.636619772 * besselJ0(x) * Math.log(x);
        } else {
            double z = 8.0 / x;
            y = z * z;
            double ax = x - 0.785398164;
            a1 = 1.0 + y * (-0.1098628627e-2 + y * (0.2734510407e-4
                    + y * (-0.2073370639e-5 + y * 0.2093887211e-6)));
            a2 = -0.1562499995e-1 + y * (0.1430488765e-3
                    + y * (-0.6911147651e-5 + y * (0.7621095161e-6
                    + y * (-0.934945152e-7))));
            a0 = Math.sqrt(0.636619772 / x) * (Math.sin(ax) * a1 + z * Math.cos(ax) * a2);
        }

        return a0;
    }


    public static double besselY1(double x) {
        if (x <= 0) throw new IllegalArgumentException("Argument must be positive.");
        double a0, a1, a2, y;
        if (x < 8.0) {
            y = x * x;
            a1 = x * (-0.4900604943e13 + y * (0.1275274390e13 + y *
                    (-0.5153438139e11 + y * (0.7349264551e9 + y * (-0.4237922726e7 + y * 0.8511937935e4)))));

            a2 = 0.2499580570e14 + y *
                    (0.4244419664e12 + y * (0.3733650367e10 + y * (0.2245904002e8 + y * (0.1020426050e6 + y * (0.3549632885e3 + y)))));

            a0 = (a1 / a2) + 0.636619772 * (besselJ1(x) * Math.log(x) - 1.0 / x);

        } else {
            double z = 8.0 / x;
            y = z * z;
            double d = x - 2.35619441;
            a1 = 1.0 + y * (0.183105e-2 + y * (-0.3516396496e-4
                    + y * (0.2457520174e-5 + y * (-0.240337019e-6))));
            a2 = 0.04687499995 + y * (-0.2002690873e-3
                    + y * (0.8449199096e-5 + y * (-0.88228987e-6
                    + y * 0.105787412e-6)));
            a0 = Math.sqrt(0.636619772 / x) * (Math.sin(d) * a1 + z * Math.cos(d) * a2);
        }

        return a0;
    }

    /**
     * @param n
     * @param x
     * @return
     */
    public static double besselY(int n, double x) {
        // use recurrence relation of bessel y functions:
        // Y_{n+1}(x) = 2*n*Y_{n}(x) / x  - Y_{n-1}(x)
        if (n <= 0) throw new IllegalArgumentException("Argument n must be positive.");
        if (n == 0) return besselY0(x);
        else if (n == 1) return besselY1(x);
        else {
            double k = 2.0 / x;
            double b1 = besselY1(x);
            double b2 = besselY0(x);
            double b = 0;
            for (int i = 1; i < n; i++) {
                b = i * k * b1 - b2;
                b2 = b1;
                b1 = b;
            }
            return b;
        }
    }

    public static double besselJ(int n, double x) {
        if (n == 0) return besselJ0(x);
        else if (n == 1) return besselJ1(x);
        else {
            double ans = 0;
            double a = Math.abs(x);
            if (a == 0.0) return 0.0;
            else if (a > n) {
                double k = 2.0 / a;
                double b = 0;
                double b1 = besselJ0(a);
                double b2 = besselJ1(a);
                for (int i = 1; i < n; i++) {
                    b = i * k * b2 - b1;
                    b1 = b2;
                    b2 = b;
                }
                ans = b2;
            } else {
                double k = 2.0 / a;
                int m = 2 * ((n + (int) Math.sqrt(40.0 * n)) / 2);
                double sum = 0.0;
                boolean flag = false;
                double bjp = 0.0;
                double bj = 1.0;
                double bjm;
                for (int i = m; i > 0; i--) {
                    bjm = i * k * bj - bjp;
                    bjp = bj;
                    bj = bjm;
                    if (Math.abs(bj) > 1.0e10) {
                        bj *= 1.0e-10;
                        bjp *= 1.0e-10;
                        ans *= 1.0e-10;
                        sum *= 1.0e-10;
                    }
                    if (flag) sum += bj;
                    flag = !flag;
                    if (i == n) ans = bjp;
                }
                sum = 2.0 * sum - bj;
                ans /= sum;
            }
            return x < 0.0 && ((n & 1) > 0) ? -ans : ans;
        }
    }


    public static double besselI0(double x) {
        double absx = Math.abs(x);
        if (absx < 3.75) {
            double y = x / 3.75;
            y *= y;
            return 1.0 + y * (3.5156229 + y * (3.0899424 + y * (1.2067492 + y * (0.2659732 + y * (0.360768E-1 + y * 0.45813E-2)))));

        } else {
            double y = 3.75 / absx;
            return (Math.exp(absx) / Math.sqrt(absx)) * (0.39894228 + y * (0.1328592E-1
                    + y * (0.225319E-2 + y * (-0.157565E-2 + y * (0.916281E-2 + y * (-0.2057706E-1 + y * (0.2635537E-1 + y * (-0.1647633E-1 + y * 0.392377E-2))))))));
        }
    }

    private static double besselK0(double x) {
        if (x <= 0) throw new IllegalArgumentException("Argument must be positive.");
        if (x <= 2.0) {
            double y = x * x / 4.0;
            return (-Math.log(x / 2.0) * besselI0(x)) + (-0.57721566 + y * (0.42278420 + y * (0.23069756 + y * (0.3488590E-1 + y * (0.262698E-2 + y * (0.10750E-3 + y * 0.74E-5))))));
        } else {
            double y = 2.0 / x;
            return (Math.exp(-x) / Math.sqrt(x)) * (1.25331414 + y * (-0.7832358E-1 + y * (0.2189568E-1 + y * (-0.1062446E-1 + y * (0.587872E-2 + y * (-0.251540E-2 + y * 0.53208E-3))))));
        }
    }

    private static double besselI1(double x) {
        double absx = Math.abs(x);
        double res;
        double y;
        if (absx < 3.75) {
            y = x / 3.75;
            y *= y;
            res = absx * (0.5 + y * (0.87890594 + y * (0.51498869 + y * (0.15084934
                    + y * (0.2658733E-1 + y * (0.301532E-2 + y * 0.32411E-3))))));
        } else {
            y = 3.75 / absx;
            res = 0.2282967E-1 + y * (-0.2895312E-1 + y * (0.1787654E-1
                    - y * 0.420059E-2));
            res = 0.39894228 + y * (-0.3988024E-1 + y * (-0.362018E-2
                    + y * (0.163801E-2 + y * (-0.1031555E-1 + y * res))));
            res *= (Math.exp(absx) / Math.sqrt(absx));
        }
        return x < 0.0 ? -res : res;
    }


    private static double besselK1(double x) {
        if (x <= 0) throw new IllegalArgumentException("Argument must be positive.");
        double res;
        double y;

        if (x <= 2.0) {
            y = x * x / 4.0;
            res = (Math.log(x / 2.0) * besselI1(x)) + (1.0 / x) * (1.0 + y * (0.15443144 + y * (-0.67278579 + y * (-0.18156897 + y * (-0.1919402E-1 + y * (-0.110404E-2 + y * (-0.4686E-4)))))));
        } else {
            y = 2.0 / x;
            res = (Math.exp(-x) / Math.sqrt(x)) * (1.25331414 + y * (0.23498619 + y * (-0.3655620E-1 + y * (0.1504268E-1 + y * (-0.780353E-2 + y * (0.325614E-2 + y * (-0.68245E-3)))))));
        }
        return res;
    }

    /**
     * Calculates the <i>Modified Bessel Function of the Second Kind, K(n,x)</i>.
     *
     * @param n
     * @param x
     * @return
     */
    public static double besselK(int n, double x) {
        if (n < 0) throw new IllegalArgumentException("Argument n must be non-negative.");
        else if (x <= 0) throw new IllegalArgumentException("Argument x must be positive.");

        double bk, bkm, bkp;
        if (n == 0) return besselK0(x);
        else if (n == 1) return besselK1(x);
        double d = 2.0 / x;
        bkm = besselK0(x);
        bk = besselK1(x);
        for (int i = 1; i < n; i++) {
            bkp = bkm + i * d * bk;
            bkm = bk;
            bk = bkp;
        }
        return bk;
    }

    /**
     * Modified <i>Bessel Function</i> for real <code>x</code>, and integer <code>n</code>.
     * @param n
     * @param x
     * @return
     */
    public static double besselI(int n, double x){
        if(n < 2) { return 0.0;}
        if(x == 0.0)
            return 0.0;
        else{
            double tox = 2.0 / Math.abs(x);
            double bip = 0.0, ans = 0.0;
            double bi = 1.0;

            for(int j = 2 * (n + (int)Math.sqrt(40.0 * n)); j > 0;j--){
                double bim = bip + j * tox * bi;
                bip = bi;
                bi = bim;
                if(Math.abs(bi) > 1E8){
                    ans *= 1E-8;
                    bi *= 1E-8;
                    bip *= 1E-8;
                }
                if(j == n) ans = bip;
            }
            ans *= besselI0(x) / bi;
            return x < 0.0 && (n == 1) ? -ans : ans;
        }
    }

}
