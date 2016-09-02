package combinatorics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import transcendence.Exp;
import utils.RootFinder;

/**
 * @author Jon Hough
 */
public class Partitions {

    /**
     * Returns the List of partitions of N, where each partition is a List of Integers.
     * Uses a recursive algorithm. For imperative algorithm that returns partitions in reverse lexicographical
     * order see <br>
     * <i><i>The Art of Computer Programming, Vol 4A 7.2.1.4 Algorithm P</i>
     *
     * @param N
     * @return ArrayList of Partitions.
     */
    public static ArrayList<ArrayList<Integer>> generatePartitions(int N) {
        //return the recursive generation.
        return generatePartitions(N, 1);
    }

    /**
     * Generates partitions of N numbers.
     * @param N
     * @param minSize
     * @return
     */
    private static ArrayList<ArrayList<Integer>> generatePartitions(int N, int minSize) {
        if (N <= 0) throw new IllegalArgumentException("Argument must be positive");
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> a = new ArrayList<Integer>();

        a.add(N);
        arr.add(a);
        if (N == 1) return arr;

        int i = minSize;
        while (i <= N - i) {
            ArrayList<ArrayList<Integer>> sub = generatePartitions(N - i, i);
            for (ArrayList<Integer> al : sub) {
                al.add(i);
            }
            i++;
            arr.addAll(sub);
        }

        return arr;
    }

    /**
     * Returns the number of m partitions of N. That is, the number of partitions of N that
     * have m members. e.g. if N = 3, m = 2 then numParts(3,2) = 1.
     * <br>
     * see <i>The Art of Computer Programming, Vol 4A 7.2.1.4 for the recursive definition</i>.
     *
     * @param N
     * @param m
     * @return
     */
    public static int numParts(int N, int m) {
        if (N == 1 && m == 1) return 1;
        else if (N == 1) return 0;
        else if (m > N) return 0;
        else if (N <= 0 || m <= 0) return 0;
        else return numParts(N - 1, m - 1) + numParts(N - m, m);
    }


    /**
     * Asymptotic estimate of P(N), where P is the number of partitions of N.
     * Uses HRR approximation function.<br>
     * Example, if N = 10, P(10) = 42, whereas this estimate will give a value of 43.
     *
     * @param N
     * @return Estimate of P(N) for large N.
     */
    public static BigInteger partition(BigInteger N) {
        if (N.compareTo(BigInteger.ZERO) <= 0)
            throw new IllegalArgumentException("Argument must be positive.");
        final int scale = 10;
        BigDecimal b = Exp.exp(Exp.E_CONST, new BigDecimal(String.valueOf(Math.PI)).multiply(
                RootFinder.squareRoot(
                        new BigDecimal("2").multiply(new BigDecimal(N)).divide(new BigDecimal("3"), scale, BigDecimal.ROUND_HALF_DOWN), scale
                )
        ).toBigInteger());

        BigDecimal div = new BigDecimal(N).multiply(new BigDecimal("4").multiply(new BigDecimal("1.73205")));

        return b.divide(div, 8, BigDecimal.ROUND_HALF_DOWN).toBigInteger();
    }


    /**
     * Calculates the estimate of the partition number of n, <i>P(n)</i>,
     * using the <i>Hardy-Ramanujan</i> asymptotic estimate, as in
     * <i> The Art of Programming, 2.7.1.4</i>
     *
     * @param n non-negative BigInteger value
     * @return partition number
     */
    public static BigDecimal calculate_HR_Estimate(BigInteger n) {
        final BigDecimal nD = new BigDecimal(n).subtract(BigDecimal.ONE.divide(new BigDecimal("24"), 20, BigDecimal.ROUND_HALF_DOWN));
        final BigDecimal FOUR = new BigDecimal("4");
        final BigDecimal THREE = new BigDecimal("3");
        final BigDecimal TWO = new BigDecimal("2");
        final BigDecimal BIG_PI = new BigDecimal(Math.PI);

        BigDecimal sqrt = RootFinder.squareRoot(nD.multiply(TWO).divide(THREE, 20, BigDecimal.ROUND_HALF_DOWN), 20);
        BigDecimal exponent = BIG_PI.multiply(sqrt);
        BigDecimal inv = BigDecimal.ONE.divide(exponent, 20, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal multiplicand = BigDecimal.ONE.divide(RootFinder.squareRoot(THREE, 20).multiply(FOUR).multiply(nD), 20, BigDecimal.ROUND_HALF_DOWN);
        return Exp.exp(exponent).multiply(multiplicand).multiply(BigDecimal.ONE.subtract(inv));
    }


//    /**
//     *
//     * @param k
//     * @param n
//     * @return
//     */
//    private static double A(int k, int n) {
//        if(n < 0 || k < 0) throw new IllegalArgumentException("Arguments must be non-negative.");
//        if(k <= 1){
//            return k;
//        }
//        else if(k == 2){
//            return Math.pow(-1, n);
//        }
//        double s = 0;
//        double r = 2;
//        double m = n % k;
//
//        for(int l = 0; l < 2 * k; l++){
//            if (m == 0) {
//                s += Math.pow(-1, l) * Math.cos(Math.PI * (( 6 * l + 1) / (6 * k)));
//            }
//            m += r;
//            if(m >= k){
//                m -= k;
//                m %= k;
//            }
//            r+=3;
//            if(r >= k){
//                r-=k;
//                r%=k;
//            }
//        }
//        return Math.sqrt(k / 3) * s;
//    }

}
