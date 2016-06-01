
package arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import factoring.Factorizor;
import transcendance.Exp;
import utils.HashMapBuilder;

/**
 * Calculations of Chebyshev's function, von Mangoldt's function
 * and related functions.
 *
 */
public class Chebyshev {

    /**
     * Returns Von Mangoldt's function for BigInteger value N.
     * 
     * @param N
     * @return
     */
    public static BigDecimal vonMangoldt(BigInteger N) {
        // factor N.
        ArrayList<BigInteger> factorList = Factorizor.factor(N, Factorizor.FactorMethod.RHO);
        // convert to hashmap.
        HashMap<BigInteger, Integer> map = HashMapBuilder.toHashMap(factorList);
        if (map == null || map.size() == 0)
            throw new IllegalStateException("Could not find factors. Factor list is empty.");
        if (map.size() > 1)
            return BigDecimal.ZERO;
        else {
            // factorlist must contain at least one element (and all elements
            // are the same)
            // so just take the first element.
            BigDecimal logP = Exp.ln(new BigDecimal(factorList.get(0)), 20);
            return logP;
        }
    }
    
    
    /**
     * 
     * @param N
     * @return
     */
    public static BigDecimal chebyshev(BigInteger N){
        BigInteger counter = BigInteger.ONE;
        BigDecimal sum = BigDecimal.ZERO;
        while (counter.compareTo(N)<= 0){
            sum = sum.add(vonMangoldt(counter));
        }
        return sum;
    }
}
