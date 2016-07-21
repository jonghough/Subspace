package combinatorics;

import java.util.ArrayList;
import java.util.Arrays;

import transcendance.Gamma;

/**
 * 
 * 
 */
public class PermutationsGeneric {

	/**
	 *
	 * @param objArray
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>> ArrayList<T[]> getLexicographicPermutations(T[] objArray) {
		int N = objArray.length;
		if (N < 1)
			throw new IllegalArgumentException("Argument array must have non-zero size.");

		Arrays.sort(objArray);
		ArrayList<T[]> permlist = new ArrayList<T[]>();
		T[] perm = objArray.clone();
		permlist.add(perm);
		T[] h = perm.clone();
		int counter = 0;
		while (counter++ < Gamma.factorial32(N) - 1) {
			h = getNextPermutation(h);
			permlist.add(h);
		}
		return permlist;
	}

	/**
	 *
	 * @param permutation
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>> T[] getNextPermutation(T[] permutation) {
		int N = permutation.length;
		T[] h = permutation.clone();
		int i = N - 2;

		while (h[i + 1].compareTo(h[i]) < 0) {
			i--;
			if (i == 0)
				break;
		}

		int j = N - 1;
		while (h[j].compareTo(h[i]) < 0) {
			j--;
		}
		T tmp = h[j];
		h[j] = h[i];
		h[i] = tmp;
		T[] p = h.clone();
		for (int k = i + 1; k < N; k++) {
			p[k] = h[k];
		}
		for (int k = i + 1; k < N; k++) {
			h[k] = p[N - 1 + i + 1 - k];
		}
		return h;
	}
}
