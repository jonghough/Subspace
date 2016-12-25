import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import primes.Sieve;

import java.util.ArrayList;

public class SieveTest {

	@Test
	public void testE() {

		assertEquals("Num. primes less than 50", 15, Sieve.eratosthenes(50)
				.size());
		assertEquals("Num. primes less than 500", 95, Sieve.eratosthenes(500)
				.size());
		assertEquals("Num. primes less than 5000", 669, Sieve
				.eratosthenes(5000).size());
		assertEquals("Num. primes less than 50000", 5133,
				Sieve.eratosthenes(50000).size());
	}

	@Test
	public void testEP() {

		assertEquals("Num. primes less than 50", 15, Sieve.eratosthenesParallel(50,6)
				.size());
		assertEquals("Num. primes less than 500", 95, Sieve.eratosthenesParallel(500,60)
				.size());
		assertEquals("Num. primes less than 5000", 669, Sieve
				.eratosthenesParallel(5000,8).size());
		assertEquals("Num. primes less than 50000", 5133,
				Sieve.eratosthenesParallel(50000,8).size());
	}

	@Test
	public void testA() {
		assertEquals("Num. primes less than 50", 15, Sieve.atkinSieve(50)
				.size());
		assertEquals("Num. primes less than 500", 95, Sieve.atkinSieve(500)
				.size());

	}
}
