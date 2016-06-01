import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import combinatorics.Permutations;

public class PermutationTest {

	@Test
	public void test1() {
		ArrayList<Integer[]> list = Permutations.getPermutations(3);
		assertEquals("Perm(3) count", 6, list.size());

	}

	@Test
	public void test2() {
		ArrayList<Integer[]> list = Permutations.getPermutations(4);
		assertEquals("Perm(4) count", 24, list.size());

	}

	@Test
	public void test3() {
		ArrayList<Integer[]> list = Permutations.getPermutations(5);
		assertEquals("Perm(5) count", 120, list.size());

	}

	@Test
	public void test4() {
		ArrayList<Integer[]> list = Permutations.getLexicographicPermutations(4);
		assertEquals("Perm(4) count", 24, list.size());

	}

	@Test
	public void test5() {
		ArrayList<Integer[]> list = Permutations.getLexicographicPermutations(5);
		assertEquals("Perm(5) count", 120, list.size());

	}

	@Test
	public void test6() {
		Integer[] nextPerm = Permutations.getNextPermutation(new Integer[] { 0, 5, 1, 2, 4, 3 });
		assertTrue("nextPerm order", nextPerm[0] == 0 && nextPerm[1] == 5 && nextPerm[2] == 1 && nextPerm[3] == 3
				&& nextPerm[4] == 2 && nextPerm[5] == 4);

	}

	@Test
	public void test7() {
		Integer[] p = Permutations.calculateUnrank(0, 5);
		boolean isIndex = true;
		for (int i = 0; i < p.length; i++) {
			isIndex &= (i == p[i]);
		}
		assertTrue("Unrank (0,5)", isIndex);

	}

	@Test
	public void test8() {
		Integer[] p = Permutations.calculateUnrank(719, 6);
		boolean isIndex = true;
		for (int i = 0; i < p.length; i++) {
			isIndex &= (i == p.length - p[i] - 1);
		}
		assertTrue("Unrank (719,6)", isIndex);

	}

	@Test
	public void test9() {
		int rank = Permutations.calculateRank(new Integer[] { 6, 5, 4, 3, 2, 0, 1 });
		assertEquals("Rank of [6,5,4,3,2,0,1]", 5038, rank);
	}

	@Test
	public void test10() {
		int rank = Permutations.calculateRank(new Integer[] { 0, 2, 3, 1 });
		assertEquals("Rank of [0,2,3,1]", 3, rank);
	}
	
	@Test
	public void test11() {
		int rank = Permutations.calculateRank(new Integer[] { 2,1,0 });
		assertEquals("Rank of [2,1,0]", 5, rank);
	}
}