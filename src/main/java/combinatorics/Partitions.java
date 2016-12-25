package combinatorics;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import transcendence.Exp;
import utils.RootFinder;

/**
 * Holds a collection of static methods for finding
 * integer partitions.
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
     * Generates the conjugate partition to the given partition. e.g. if
     * the <i>partition</i> is represented as: <br>
     *     <code>* * * *<br>
     *           * *<br>
     *           *<br>
     *     </code><br>
     * then the <i>conjugate partition</i> can be represented as: <br>
     *     <code>* * *<br>
     *           * *<br>
     *           *<br>
     *           *<br>
     *     </code><br>
     *         i.e. rows and columns are interchanged.
     * @param partition partition from which to generate the conjugate.
     * @return conjugate partition.
     */
    public static ArrayList<Integer> conjugatePartition(ArrayList<Integer> partition){
        int l = partition.get(0);
        ArrayList<Integer> init = new ArrayList<>();
        for(int i = 0; i <= partition.get(0);i++){
            init.add(1);

        }
        int np = partition.get(0);
        for(int j = 1; j < partition.size(); j++){
            for(int i = 0; i < partition.get(j); i++){
                if(i < init.size())
                    init.set(i, init.get(i) + 1);
                else init.add(1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.addAll(init.subList(0, np));
        return res;
    }

    /**
     *
     * Returns the lexicographic successor parititon of the given partition, <code>partition</code>,
     * of integer <code>m</code> of size <code>n</code>. e.g.
     * <code>successor(5,3,{3,1,1});</code><br>
     *     returns<br>
     *     <code>{2,2,1}</code>
     * @param m
     * @param n
     * @param partition
     * @return
     */
    public static ArrayList<Integer> successor(int m, int n, ArrayList<Integer> partition){
        int i = 1;
        while(i < n && partition.get(0) <= partition.get(i) + 1){
            i++;
        }
        if(i >= n){
            throw new RuntimeException("No partition found");
        }
        else{
            ArrayList<Integer> p2 = new ArrayList<Integer>(partition);
             p2.set(i, partition.get(i) + 1);
            int d = -1;
            for(int j = i-1 ;  j >= 1; j--){
                d = d + p2.get(j) - p2.get(i);
                p2.set(j, p2.get(i));
            }
            p2.set(0, p2.get(0) + d);
            return p2;
        }

    }


    public static int enumeratePartitions(int m, int n) {
        HashMap<PartitionPair, Integer> hashMap = new HashMap<>();
        hashMap.put(new PartitionPair(0,0), 1);

        for(int i = 1; i <= m; i++){
            hashMap.put(new PartitionPair(i,0), 0);
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= Math.min(i,n); j++){
                if(i < 2 * j){
                    hashMap.put(new PartitionPair(i, j), hashMap.get(new PartitionPair(i-1, j - 1)));
                }
                else{
                    hashMap.put(new PartitionPair(i, j), hashMap.get(new PartitionPair(i-1, j - 1)) + hashMap.get(new PartitionPair(i - j, j)));
                }
            }
        }

        return hashMap.get(new PartitionPair(m,n));
    }

    /**
     * Gets the lexicographic rank of the partition, where the partition is a
     * partition of the number <i>m</i> with <i>n</i> parts.<br>
     *     For example:<br>
     *         the partition (of 17) given as <i>{5,5,4,2,1}</i><br>
     *         We can find the rank by:<br>
     *               <code>Partitions.lexicographicRank(17,5,p);</code><br>
     *       which should be <code>28</code>.
     * @param m
     * @param n
     * @param partition
     * @return
     */
    public static int lexicographicRank(int m, int n, ArrayList<Integer> partition){

        ArrayList<Integer> parts = new ArrayList<>();
        for(int i = 0; i < partition.size(); i++){
            parts.add(partition.get(i));

        }
        int k = 0;
        int M = m;
        int N = n;
        while(M > 0){
            if(parts.get(N-1) == 1){
                M--;
                N--;
            }
            else{
                for(int i = 0; i < N;i++){
                    parts.set(i, parts.get(i) - 1);
                }
                k += enumeratePartitions(M-1, N-1);
                M -= N;
            }
        }
        return k;
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



}


class PartitionPair{
    public final int m;
    public final int n;

    public PartitionPair(int m_, int n_){
        m = m_; n = n_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartitionPair that = (PartitionPair) o;

        if (m != that.m) return false;
        return n == that.n;

    }

    @Override
    public int hashCode() {
        int result = m;
        result = 31 * result + n;
        return result;
    }
}