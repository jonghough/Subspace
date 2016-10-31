import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import factoring.Factorizor;
import combinatorics.Partitions;

public class PartitionsTest {


    @Test
    public void test() {

        assertEquals("Size of Partition set of 5", 7, Partitions.generatePartitions(5).size());
        assertEquals("Size of Partition set of 10", 42, Partitions.generatePartitions(10).size());
        assertEquals("Size of Partition set of 12", 77, Partitions.generatePartitions(12).size());
        assertEquals("Size of Partition set of 40", 37338, Partitions.generatePartitions(40).size());
    }

    @Test
    public void test2() {
        // test conjugate partitions.
        ArrayList<Integer> part = Partitions.generatePartitions(5).get(6);

        ArrayList<Integer> conj = Partitions.conjugatePartition(part);

        assertEquals("part[0] = 3", 3, (int)part.get(0));
        assertEquals("part[1] = 2", 2, (int)part.get(1));

        assertEquals("conj[0] = 2", 2, (int)conj.get(0));
        assertEquals("conj[1] = 2", 2, (int)conj.get(1));
        assertEquals("conj[2] = 1", 1, (int)conj.get(2));
    }


    @Test
    public void test3() {
        // test conjugate partitions.
        ArrayList<Integer> part = Partitions.generatePartitions(10).get(0);

        ArrayList<Integer> conj = Partitions.conjugatePartition(part);

        assertEquals("part[0] = 10", 10, (int)part.get(0));

        assertEquals("conj[0] = 1", 1, (int)conj.get(0));
        assertEquals("conj[9] = 1", 1, (int)conj.get(9));
    }



    @Test
    public void test4(){
        Integer[] part = new Integer[]{5,5,4,2,1};
        ArrayList<Integer> p;
        p = new ArrayList<Integer>(Arrays.asList(part));
        ArrayList<Integer> next = Partitions.successor(17,5, p);
        String s = new String();
        for(Integer h : next){
            s += ""+h;
        }

        assertEquals("next partition is 73331", "73331",s);
    }

    @Test
    public void test5(){
        Integer[] part = new Integer[]{3,1,1};
        ArrayList<Integer> p;
        p = new ArrayList<Integer>(Arrays.asList(part));
        ArrayList<Integer> next = Partitions.successor(5,3, p);
        String s = new String();
        for(Integer h : next){
            s += ""+h;
        }

        assertEquals("next partition is 221", "221",s);
    }

    @Test
    public void test6(){
        int i = Partitions.enumeratePartitions(12,5);
        assertEquals("P(12,5) = 13", 13, i) ;

    }

    @Test
    public void test7(){
        int i = Partitions.enumeratePartitions(8,3);
        assertEquals("P(8,3) = 5", 5, i) ;

    }

    @Test
    public void test8(){
        Integer[] part = new Integer[]{5,5,4,2,1};
        ArrayList<Integer> p = new ArrayList<Integer>(Arrays.asList(part));
        int k = Partitions.lexicographicRank(17,5,p);

        assertEquals("lex rank (17,5,[5,5,4,2,1] ) is 28", 28, k);
    }


    @Test
    public void estimateTest() {

        BigDecimal p = Partitions.calculate_HR_Estimate(new BigInteger(String.valueOf(100)));
        BigInteger diff = p.subtract(new BigDecimal("190568944")).toBigInteger();
        assertEquals(BigInteger.ZERO, diff);

    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTest1() {
        Partitions.generatePartitions(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTest2() {
        Partitions.generatePartitions(0);
    }
}