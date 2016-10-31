
package factoring;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import arithmetic.JacobiSymbol;
import primes.BSmooth;
import primes.Sieve;
import utils.RootFinder;

/**
 * Incomplete. UNDER CONSTRUCTION
 *
 */
public class QuadraticSieve {


    private BigInteger fo(BigInteger n, BigInteger p){
        BigInteger d = n;
        while(d.mod(p).compareTo(BigInteger.ZERO) == 0){
            d = d.mod(p);
        }
        return d;
    }

    public static BigInteger factor(BigInteger N){
        BigDecimal b = RootFinder.squareRoot(BSmooth.L(N), 10);
        if(b.toBigInteger().compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) throw new
                IllegalArgumentException("Arguemnt is too big to factor with quadratic sieve");

        else{
            int bInt = b.add(BigDecimal.ONE).toBigInteger().intValue();
            ArrayList<Integer> sieved = Sieve.eratosthenes(bInt);
            ArrayList<Integer> qresidues = new ArrayList<>();
            for(Integer i : sieved){
                if(JacobiSymbol.calculateJacobi(N, new BigInteger(String.valueOf(i))) == 1){
                    qresidues.add(i);
                }
            }
            // ----
            ArrayList<BigInteger> r = new ArrayList<>();
            ArrayList<BigInteger> l = new ArrayList<>();
            HashMap<Integer,Integer> factorMultipleMap = new HashMap<>();
            for(Integer i : qresidues){
                BigInteger k = new BigInteger(String.valueOf(i * i)).subtract(N);

                for(int prime : sieved){
                    BigInteger bp = new BigInteger(String.valueOf(prime));
                    while(k.mod(bp).compareTo(BigInteger.ZERO) == 0){
                        k = k.mod(bp);
                    }

                }
                if(k.compareTo(BigInteger.ONE) == 0)
                    r.add(k);
            }


            // ----
//            for(Integer i : qresidues) {
//                BigInteger s = RootFinder.squareRoot(new BigDecimal(N.mod(new BigInteger(String.valueOf(i)))),20).toBigInteger();
//
//            }
        }
        return null;
    }




	/**
	 * 
	 * @param N
	 * @return
	 */
    public static BigInteger CalcB(BigInteger N){
    	BigDecimal B = RootFinder.squareRoot(BSmooth.L(N), 10);
    	return B.toBigInteger().add(BigInteger.ONE);
    }
    
    public static ArrayList<Integer> GetFPrimes(Integer N){
    	ArrayList<Integer> primes = Sieve.atkinSieve(N);
    	ArrayList<Integer> fPrimes = new ArrayList<Integer>();
    	for(Integer p : primes){
    		if(JacobiSymbol.calculateJacobi(new BigInteger(p.toString()), new BigInteger(N.toString()))==1){
    			fPrimes.add(p);
    		}
    	}
    	return fPrimes;
    }
    

    /**
     * @param n
     * @param root
     * @return
     */
    private static ArrayList<BigInteger> createInitialFactorList(BigInteger n, BigInteger root) {
        ArrayList<BigInteger> list = new ArrayList<BigInteger>();
        BigInteger init = root;
        while (init.compareTo(root.multiply(new BigInteger("2"))) < 0) {
            list.add(F(n, init));
            init = init.add(BigInteger.ONE);
        }

        return list;
    }
    
    /**
     * 
     * @param list
     * @param divisor
     */
    private static void cancelBy(BigInteger n, ArrayList<BigInteger> list, BigInteger divisor){
        BigInteger moduloN = n.mod(divisor);
        for(BigInteger bi : list){
            if((bi.multiply(bi)).mod(divisor).compareTo(moduloN)==0){
                bi = bi.divide(divisor);
            }
        }
    }

    
    private ArrayList<BigInteger> selectOnes(ArrayList<BigInteger> list){
        Iterator<BigInteger> iterator = list.iterator();
        while(iterator.hasNext()){
            BigInteger value = iterator.next();
            if(value.compareTo(BigInteger.ONE)!=0){
                iterator.remove();
            }
        }
        return list;
        
    }
    
    /**
     * Calculates (X-sqrt(N))^2 - N.
     * @param X
     * @param N
     * @return
     */
    private static BigInteger square(BigInteger X, BigInteger N){
        BigInteger floorN = RootFinder.getRootFloor(N);
        BigInteger Y = (X.add(floorN)).multiply(X.add(floorN)).subtract(N);
        return Y;
    }
    /**
     * @param n
     * @param root
     * @return
     */
    private static BigInteger F(BigInteger n, BigInteger root) {
        return (root.multiply(root)).subtract(n);
    }
}
