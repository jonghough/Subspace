package arithmetic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import factoring.Factorizor;
import utils.HashMapBuilder;

/**
 * Divisor sum functions.
 *
 */
public class Sigma {
    
   
    /**
     * Calculates the divisor sum function of n, with the given subscript.<br>
     * e.g. if subscript is 0, this will calculate the number of divisors of n.
     * If subscript is 1, will calculate the sum of the divisors of n.
     * @param subscript
     * @param n
     * @return Divisor sum of n.
     */
    public static BigInteger sigma(int subscript, BigInteger n){
        if(subscript < 0) throw new IllegalArgumentException("Cannot have negative subscript");
        if(subscript == 0) return sigma0(n);
        else{
            BigInteger val;
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            
            ArrayList<BigInteger> factorList = Factorizor.factor(n, Factorizor.FactorMethod.RHO);
            HashMap<BigInteger, Integer> map = HashMapBuilder.toHashMap(factorList);
            
            Iterator<Entry<BigInteger, Integer>> iter = map.entrySet().iterator();

            while (iter.hasNext()) {
                Entry<BigInteger, Integer> entry = iter.next();
                val = entry.getKey().pow((entry.getValue() + 1) * subscript).subtract(BigInteger.ONE);
                numerator = numerator.multiply(val);
                denominator = denominator.multiply(entry.getKey().pow(subscript).subtract(BigInteger.ONE));
            }
            
            return numerator.divide(denominator);
        }
    }

    /**
     * Calculates Sigma_0(n) for bigInteger n.<br> 
     * Essentially calculates the number of positive divisors of n.
     * @param n bigInteger value
     * @return number of positive divisors
     */
    private static BigInteger sigma0(BigInteger n){
        ArrayList<BigInteger> factorList = Factorizor.factor(n, Factorizor.FactorMethod.RHO);

        HashMap<BigInteger, Integer> map = HashMapBuilder.toHashMap(factorList);
        BigInteger prod = BigInteger.ONE;
        Iterator<Entry<BigInteger, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<BigInteger, Integer> entry = iter.next();
            prod = prod.multiply(new BigInteger(String.valueOf(entry.getValue() + 1)));
        }
        
        return prod;
    }
}
