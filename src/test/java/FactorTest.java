import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import factoring.Factorizor;
import transcendance.Gamma;

public class FactorTest {

	/**
	 * Tests all perform Pollard p-1 factorization algorithm and if failure after x iterations switches to 
	 * Pollard rho algorithm.
	 */
	@Test
	public void test() {

//		assertEquals("Factor list of 3035576764345345055113211, size: ", 2, Factorizor.factor(new BigInteger(
//				"3035576764345345055113211"), Factorizor.FactorMethod.RHO).size());
//
//		assertEquals("Factor list of 30355767643453450551132110100200001, size: ", 5, Factorizor.factor(new BigInteger(
//				"30355767643453450551132110100200001"), Factorizor.FactorMethod.RHO).size());
//
//		assertEquals("Factor list of 94054059932039392458577323940987547527, size: ", 2, Factorizor.factor(new BigInteger(
//				"94054059932039392458577323940987547527"), Factorizor.FactorMethod.RHO).size());
//
//		assertEquals("Factor list of 8438503049348381100385800049534923490020044110031, size: ", 4, Factorizor.factor(new BigInteger(
//				"8438503049348381100385800049534923490020044110031"), Factorizor.FactorMethod.RHO).size());

		assertEquals(3, Factorizor.factor(new BigInteger("3024041"), Factorizor.FactorMethod.PMO).size());
		assertEquals(4, Factorizor.factor(new BigInteger("20492001"), Factorizor.FactorMethod.PMO).size());
		assertEquals(2, Factorizor.factor(new BigInteger("20492001030307"), Factorizor.FactorMethod.PMO).size());
		assertEquals(5, Factorizor.factor(new BigInteger("2302048929391113"), Factorizor.FactorMethod.PMO).size());
		assertEquals(6, Factorizor.factor(new BigInteger("8392894255239922239"), Factorizor.FactorMethod.PMO).size());


	}
}
