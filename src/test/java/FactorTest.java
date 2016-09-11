import factoring.Factorizor;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class FactorTest {


//	@Test
//	public void test1() {
//		assertTrue(2 == Factorizor.factor(new BigInteger("20492001030307"), Factorizor.FactorMethod.PMO).size());
//		assertTrue(5 == Factorizor.factor(new BigInteger("2302048929391113"), Factorizor.FactorMethod.PMO).size());
//
//	}
//
//	@Test
//	public void test2(){
//		assertTrue("Factor list of 3035576764345345055113211, size: ", 2 == Factorizor.factor(new BigInteger(
//				"3035576764345345055113211"), Factorizor.FactorMethod.RHO).size());
//	}
//
//	@Test
//	public void test3(){
//		assertTrue("Factor list of 100, size: ", 4 == Factorizor.factor(new BigInteger(
//				"100"), Factorizor.FactorMethod.PMO).size());
//	}
//
//	@Test
//	public void test4(){
//		assertTrue("Factor list of 8392894255239922239, size: ", 6 == Factorizor.factor(
//				new BigInteger("8392894255239922239"), Factorizor.FactorMethod.PPO).size());
//	}
//
//	@Test
//	public void test5(){
//		assertTrue("Factor list of 8392894255239922239, size: ", 6 == Factorizor.factor(
//				new BigInteger("8392894255239922239"), Factorizor.FactorMethod.ECF).size());
//	}
//
//	@Test
//	public void test6(){
//		assertTrue("Factor list of 8438503049348381100385800049534923490020044110031, size: ", 4== Factorizor.factor(new BigInteger(
//				"8438503049348381100385800049534923490020044110031"), Factorizor.FactorMethod.RHO).size());
//	}

	@Test
	public void test7(){
		assertTrue(4 == Factorizor.factor(new BigInteger("2010"), Factorizor.FactorMethod.ECF).size());
	}

	@Test
	public void test8(){
		assertTrue(4 == Factorizor.factor(new BigInteger("2010"), Factorizor.FactorMethod.PMO).size());
	}

	@Test
	public void test9(){
		assertTrue(4 == Factorizor.factor(new BigInteger("2010"), Factorizor.FactorMethod.PPO).size());
	}

	@Test
	public void test10(){
		assertTrue(4 == Factorizor.factor(new BigInteger("2010"), Factorizor.FactorMethod.RHO).size());
	}
}
