import static org.junit.Assert.*;
import org.junit.Test;

import arithmetic.Sigma;
import static org.junit.Assert.*;

import java.math.BigInteger;

public class SigmaTest {

	@Test
	public void test() {
		//simple small integers
		assertEquals("sigma_0 230 = 8", new BigInteger("8"), Sigma.sigma(0, new BigInteger("230")));
		assertEquals("sigma_0 7 = 2", new BigInteger("2"), Sigma.sigma(0, new BigInteger("7")));
		assertEquals("sigma_1 12 = 28", new BigInteger("28"), Sigma.sigma(1, new BigInteger("12")));
		assertEquals("sigma_1 439 = 440", new BigInteger("440"), Sigma.sigma(1, new BigInteger("439")));
	}
}
