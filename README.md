# Subspace

## Math library, written in Java, focusing on number theory and related fields.

### Implemented functions:

| Functions                                 | Implemented                                                                                                                 |
|-------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Factorization                             | Rho, P-1, P+1, Elliptic Curve examples                                                                                      |
| Arithmetic and Number theoretic functions | Prime decomposition, totient function, sigma functions, primality test, Meissel formula for  counting primes, Jacobi Symbol |
| Analytic NT functions                     | Gamma function, approximation for large values, Riemann-Siegel formula for Z(t)                                             |
| Sieves                                    | Eratosthenes, Atkin sieves                                                                                                  |
| Combinatorics                             | Integer Partitions, Partition counting, Permutation, lexicographically ordered permutations, permutation rank, parity.      |

## License
BSD 2-clause “Simplified” License

### Build jar file

`ant build-jar`

### Run tests

`mvn test`

### Examples 

**<i>Factoring a BigInteger</i>**
The following uses the Pollard-rho method of factorization:

`ArrayList<BigInteger> list = Factorizor.factor(new BigInteger("909077759200010102032010201"), Factorizor.FactorMethod.RHO);`

will return

`967, 9819889267, 95734388625509`

Pollard P-1 method is also implemented:

`ArrayList<BigInteger> list2 = Factorizor.factor(new BigInteger("425624900000909000001111317911"), Factorizor.FactorMethod.PMO);`

returns 

 `571, 62818373, 11865997022428766417`
 
 Factorizor also includes a P+1 factorization algorithm implementation.
 
 
**<i>Euler Totient of a BigInteger</i>**
 
 Euler totient is defined [Totient](https://en.wikipedia.org/wiki/Euler%27s_totient_function)
 
 `BigInteger tot = Totient.totient(new BigInteger("425624"));`
 
 returns
 
 `209920`
 
 **<i> Calculating number of primes less than a given float (or BigDecimal)</i>**
 
 `int q = PrimeCount.pi(1000000f);`
 
 returns
 
 `78498`
 
 This uses the Meissel formula to calculate pi(x). It will probably choke on values much larger than 10 million though.
