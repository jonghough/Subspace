import static org.junit.Assert.*;

import java.math.BigInteger;
import org.junit.Test;
import arithmetic.JacobiSymbol;

/**
 * Unit Tests for Jacobi Symbol calculations.
 *
 *
 */
public class JacobiTest {

	@Test
	public void test1() {

		// Tests
		assertEquals("201 / 100 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("100"), new BigInteger("201")));
	}

	@Test
	public void test2() {
		assertEquals("228530738017 / 9365449244297 = -1", -1,
				JacobiSymbol.calculateJacobi(new BigInteger("228530738017"), new BigInteger("9365449244297")));
	}

	@Test
	public void test3() {
		assertEquals("-1 / 3 = -1", -1, JacobiSymbol.calculateJacobi(new BigInteger("-1"), new BigInteger("3")));

	}

	@Test
	public void test5() {
		assertEquals("2 / 5 = -1", -1, JacobiSymbol.calculateJacobi(2L, 5L));
	}

	@Test
	public void test6() {
		assertEquals("3 / 5 = -1", -1, JacobiSymbol.calculateJacobi(3L, 5L));
	}

	@Test
	public void test7() {
		assertEquals("4 / 5 = 1", 1, JacobiSymbol.calculateJacobi(4L, 5L));
	}

	@Test
	public void test8() {
		assertEquals("0 / 5 = 0", 0, JacobiSymbol.calculateJacobi(0L, 5L));
	}

	@Test
	public void test9() {
		assertEquals("10 / 200 = 1", 1, JacobiSymbol.calculateJacobi(10L, 200L));
	}

	@Test
	public void test10() {
		assertEquals("10 / 200 = 0", 0, JacobiSymbol.calculateJacobi(new BigInteger("10"), new BigInteger("200")));
	}

	@Test
	public void test11() {
		assertEquals("2 / 7 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("2"), new BigInteger("7")));
	}

	@Test
	public void test12() {
		assertEquals("3 / 7 = -1", -1, JacobiSymbol.calculateJacobi(new BigInteger("3"), new BigInteger("7")));
	}

	@Test
	public void test13() {
		assertEquals("4 / 7 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("4"), new BigInteger("7")));
	}

	@Test
	public void test14() {
		assertEquals("5 / 7 = -1", -1, JacobiSymbol.calculateJacobi(new BigInteger("5"), new BigInteger("7")));
	}

	@Test
	public void test15() {
		assertEquals("1 / 7 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("1"), new BigInteger("7")));
	}

	@Test
	public void test16() {
		assertEquals("0 / 7 = 0", 0, JacobiSymbol.calculateJacobi(new BigInteger("0"), new BigInteger("7")));
	}

	@Test
	public void test17() {
		assertEquals("1 / 15 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("1"), new BigInteger("15")));
	}

	@Test
	public void test18() {
		assertEquals("2 / 15 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("2"), new BigInteger("15")));
	}

	@Test
	public void test19() {
		assertEquals("3 / 15 = 0", 0, JacobiSymbol.calculateJacobi(new BigInteger("3"), new BigInteger("15")));
	}

	@Test
	public void test20() {
		assertEquals("4 / 15 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("4"), new BigInteger("15")));
	}

	@Test
	public void test21() {
		assertEquals("9 / 15 = 0", 0, JacobiSymbol.calculateJacobi(new BigInteger("9"), new BigInteger("15")));
	}

	@Test
	public void test22() {
		// 100 = 2*2*5*5 so (a/100) is +1 for all a.
		assertEquals("93 / 100 = 1", 1, JacobiSymbol.calculateJacobi(new BigInteger("93"), new BigInteger("100")));
	}
}
