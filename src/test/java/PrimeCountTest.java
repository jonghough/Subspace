import static org.junit.Assert.*;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.BigInteger;


import primes.PrimeCount;

/**
 * Tests for counting number of primes not exceeding real number x.
 * i.e. pi(x). For values of x less than 2, pi(x) is regarded as zero.
 * <br>
 * Tests the calculation of the nth prime, for positive integer n, starting with the
 * first prime = 2.
 * @author Jon Hough
 *
 */
public class PrimeCountTest {

	@Test
	public void test() {

		// Tests
		//pi(x) tests
		assertEquals("pi(-1) = 0", 0, PrimeCount.pi(-1));
		assertEquals("pi(0) = 0", 0, PrimeCount.pi(0));
		assertEquals("pi(2) = 1", 1, PrimeCount.pi(2));
		assertEquals("pi(10) = 4", 4, PrimeCount.pi(10));
		assertEquals("pi(100) = 25", 25, PrimeCount.pi(100));
		assertEquals("pi(1000) = 168", 168, PrimeCount.pi(1000));
		assertEquals("pi(10000) = 1229 ", 1229, PrimeCount.pi(10000));
		assertEquals("pi(100000) = 9592", 9592, PrimeCount.pi(100000));
		assertEquals("pi(100000) = 78498", 78498, PrimeCount.pi(1000000));
		//nth prime tests
		assertEquals("1st prime is 2", 2, PrimeCount.calcNthPrime(1));
		assertEquals("10th prime is 29", 29, PrimeCount.calcNthPrime(10));
		assertEquals("100th prime is 541", 541, PrimeCount.calcNthPrime(100));
		assertEquals("1000th prime is 7919", 7919, PrimeCount.calcNthPrime(1000));
		assertEquals("10000th prime is 104729", 104729, PrimeCount.calcNthPrime(10000));
		//BigIntegers

		assertEquals("pi(30490) = 3290", new BigInteger("3290"), PrimeCount.pi(new BigDecimal("30490")));
		assertEquals("10000th prime is 104729", new BigInteger("104729"), PrimeCount.calcNthPrime(new BigInteger("10000")));
	}
}
