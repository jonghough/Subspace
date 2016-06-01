package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Builds hashmaps from BigInteger lists.
 * @author Jon Hough
 *
 */
public class HashMapBuilder {

    /**
     * 
     * @param bigIntList
     * @return Hashmap
     */
    public static HashMap< BigInteger, Integer> toHashMap(ArrayList<BigInteger> bigIntList){
        HashMap<BigInteger, Integer> map = new HashMap<BigInteger, Integer>();
    
        for(BigInteger big : bigIntList){
            Integer counter= map.get(big); 
            map.put(big, counter == null ? 1 : counter + 1);
        }
        return map;
    }
}
