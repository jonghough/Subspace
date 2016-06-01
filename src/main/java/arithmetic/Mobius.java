package arithmetic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import factoring.Factorizor;
import utils.HashMapBuilder;

/**
 * Holds static methods for Mobius function.
 *
 */
public class Mobius {

    /**
     * Finds the mobius value of integer N. That is, <br>
     * mobius(N) =:<br>
     * 1 if N has an even number of square-free prime divisors;<br>
     * -1 if N has an odd number of square-free prime divisors;<br>
     * 0 if N has a square prime factor.
     * @param N BigInteger 
     * @return Mobius mu(N)
     */
    public static int mobius(BigInteger N){
        ArrayList<BigInteger> factors = Factorizor.factor(N, Factorizor.FactorMethod.RHO);
        HashMap<BigInteger, Integer> factorMap = HashMapBuilder.toHashMap(factors);
        int len = factorMap.size();
        int mob = 1;
        mob *= len % 2 == 0 ? 1 : -1;
        Iterator<Entry<BigInteger, Integer>> iter = factorMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<BigInteger, Integer> entry = iter.next();
            if(entry.getValue() > 1){
                mob = 0;
                break;
            }
        }
        return mob;
    }
    
    /**
     * Returns the sum of the mobius of all positive integers less
     * than or equal to N.
     * @param N
     * @return
     */
    public static int M(BigInteger N){
        BigInteger inc = N;
        int m = 0;
        while(inc.compareTo(BigInteger.ONE) > 0){
            m += mobius(inc);
            inc = inc.subtract(BigInteger.ONE);
        }
        return m;
    }
    
}
