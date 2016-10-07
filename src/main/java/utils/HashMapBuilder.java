package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Builds hashmaps from BigInteger lists.
 */
public class HashMapBuilder {

    /**
     * Builds a hashmap where keys are <code>BigInteger</code>s and values are <code>Integer</code>s.<br>
     * In most cases the values represent the multiplicity of a prime factor of some number, where the
     * factor is the key.
     *
     * @param bigIntList
     * @return Hashmap
     */
    public static HashMap<BigInteger, Integer> toHashMap(ArrayList<BigInteger> bigIntList) {
        HashMap<BigInteger, Integer> map = new HashMap<BigInteger, Integer>();

        for (BigInteger big : bigIntList) {
            Integer counter = map.get(big);
            map.put(big, counter == null ? 1 : counter + 1);
        }
        return map;
    }
}
