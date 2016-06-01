import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

import factoring.Factorizor;
import combinatorics.Partitions;

public class PartitionsTest {

	
	@Test
	public void test() {

		assertEquals("Size of Partition set of 5", 7, Partitions.generatePartitions(5).size());
		assertEquals("Size of Partition set of 10", 42,Partitions.generatePartitions(10).size());
		assertEquals("Size of Partition set of 12", 77, Partitions.generatePartitions(12).size());
		assertEquals("Size of Partition set of 40", 37338, Partitions.generatePartitions(40).size());
	}

	@Test
	public void estimateTest(){

			BigDecimal p = Partitions.calculate_HR_Estimate(new BigInteger(String.valueOf(100)));
			BigInteger diff = p.subtract(new BigDecimal("190568944")).toBigInteger();
			assertEquals(BigInteger.ZERO, diff);
			
	}

	@Test(expected=IllegalArgumentException.class)
	public void exceptionTest1(){
		Partitions.generatePartitions(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void exceptionTest2(){
		Partitions.generatePartitions(0);
	}
}