package combinatorics;

import java.util.ArrayList;
import java.util.HashSet;

import transcendence.Gamma;

public class Permutations {

	/**
	 * Generates all possible permutations on <i>N</i> letters, returning arrays
	 * of the indices <i>{0,..., N-1}</i>. There are clearly N! such
	 * permutations. Algorithm is taken from <i>Art of Computer Programming,
	 * 7.2.1.2, Algorithm P</i>.
	 * 
	 * @param N
	 *            positive integer.
	 * @return ArrayList of integer arrays, each representing a permutation.
	 */
	public static ArrayList<Integer[]> getPermutations(int N) {
		if (N <= 0)
			throw new IllegalArgumentException("Cannot have negative argument.");
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] a = new Integer[N];
		Integer[] o = new Integer[N];
		Integer[] c = new Integer[N];
		for (int i = 0; i < N; i++) {
			a[i] = i;
			o[i] = 1;
			c[i] = 0;
		}
		for (int k = 0; k < Gamma.factorial(N).intValue(); k++) {
			list.add(new Integer[N]);
		}
		int r = 0;
		int j = N - 1;
		int k = 26;
		while (j != 0 && k-- > 0) {
			int z = 0;
			j = N - 1;
			int q = c[j] + o[j];
			for (int i = 0; i < N - 1; i++) {
				list.set(r, a.clone());
				r++;
				int alpha = j - c[j] + z;
				int beta = j - q + z;

				int tmp = a[alpha];
				a[alpha] = a[beta];
				a[beta] = tmp;
				c[j] = q;
				q = c[j] + o[j];
			}

			list.set(r, a.clone());
			r++;
			while (q < 0) {
				o[j] = -1 * o[j];
				j--;
				q = c[j] + o[j];
			}
			while (q == (j + 1) && j != 0) {
				z++;
				o[j] = -1 * o[j];
				j--;
				q = c[j] + o[j];
			}

			if (j != 0) {
				int alpha = j - c[j] + z;
				int beta = j - q + z;
				int tmp = a[alpha];
				a[alpha] = a[beta];
				a[beta] = tmp;
				c[j] = q;
			}
		}
		return list;
	}

	/**
	 * Returns all permutations on N letters, in numeric order.
	 * 
	 * @param N
	 */
	public static ArrayList<Integer[]> getLexicographicPermutations(int N) {
		if (N < 1)
			throw new IllegalArgumentException("Cannot use non-positive arguments");
		ArrayList<Integer[]> permlist = new ArrayList<Integer[]>();
		Integer[] perm = new Integer[N];
		for (int i = 0; i < N; i++)
			perm[i] = i;
		permlist.add(perm);
		Integer[] h = perm.clone();
		int counter = 0;
		while (counter++ < Gamma.factorial32(N) - 1) {
			h = getNextPermutation(h);
			permlist.add(h);
		}
		return permlist;
	}

	/**
	 * Returns the next lexicographically ordered permutation after the given
	 * permutation.
	 * 
	 * @param permutation
	 * @return the next permutation.
	 */
	public static Integer[] getNextPermutation(Integer[] permutation) {
		int N = permutation.length;
		Integer[] h = permutation.clone();
		int i = N - 2;

		while (h[i + 1] < h[i]) {
			i--;
			if (i == 0)
				break;
		}

		int j = N - 1;
		while (h[j] < h[i]) {
			j--;
		}
		int tmp = h[j];
		h[j] = h[i];
		h[i] = tmp;
		Integer[] p = h.clone();

		for (int k = i + 1; k < N; k++) {
			h[k] = p[N - 1 + i + 1 - k];
		}
		return h;
	}

	/**
	 * Calculates the rank of the given permutation on N letters, where N is the
	 * size of the permutation array.
	 * 
	 * @param permutation
	 * @return
	 */
	public static int calculateRank(Integer[] permutation) {
		if (!isPermutation(permutation))
			throw new IllegalArgumentException("Argument is not a permutation.");
		Integer[] perm = permutation.clone(); // copy the original.
		int size = perm.length;
		int r = 0;
		for (int j = 0; j < size; j++) {
			r += perm[j] * Gamma.factorial32(size - j - 1);
			for (int i = j + 1; i < size; i++) {
				if (perm[i] > perm[j])
					perm[i] = perm[i] - 1;
			}
		}
		return r;
	}

	/**
	 * Calculates the unrank of integer r, in the lexicographically ordered set
	 * of permutations on n letters. Clearly, r must be in range [0,n!). <br>
	 * Example:<br>
	 * <code>calculateUnrank(3, 5);</code> will return the Integer array
	 * <code> [0, 1, 3, 4, 2]</code> since this is the rank-3 permutation on 5
	 * letters (index starting at 0). <br>
	 * 
	 * @param r
	 *            rank
	 * @param n
	 *            size of permutation.
	 * @return the unrank of r.
	 */
	public static Integer[] calculateUnrank(int r, int n) {
		if (r < 0 || r >= Gamma.factorial32(n))
			throw new IllegalArgumentException("Argument r is not in valid range.");
		Integer[] perm = new Integer[n];
		perm[n - 1] = 0;
		for (int j = 0; j < n - 1; j++) {
			int d = (r % Gamma.factorial32(j + 2)) / Gamma.factorial32(j + 1);
			r -= d * Gamma.factorial32(j);
			perm[n - j - 2] = d;
			for (int i = n - j - 1; i < n; i++) {
				if (perm[i] > d - 1) {
					perm[i] = perm[i] + 1;
				}
			}
		}
		return perm;
	}
	
	/**
	 * Returns the parity of the given permutation. Parity is 1 if the
	 * permutation can be rewritten as an even number of permutations,
	 * and zero otherwise.
	 * @param permutation
	 * @return 0 if even parity, 1 if odd.
	 */
	public static int parity(Integer[] permutation){
		int size = permutation.length;
		Integer[] perm = permutation.clone();
		for(int i = 0; i < size; i++){
			perm[i] = 0;
		}
		int k = 0;
		for(int j = 0; j < size; j++){
			if(perm[j] == 0){
				k++;
				perm[j] = 1;
				int i = j;
				while(permutation[i] != j){
					i = permutation[i];
					perm[i] = 1;
				}
			}
		}
		return (size - k) % 2;
	}

	/**
	 * Check if the given integer array is a permutation. To be a permutation
	 * the array must contain some permutation of the integers [0,..., length -
	 * 1].
	 * 
	 * @param permutation
	 * @return
	 */
	private static boolean isPermutation(Integer[] permutation) {
		int size = permutation.length;
		HashSet<Integer> hashSet = new HashSet<Integer>();
		int sum = 0;
		for (int i = 0; i < permutation.length; i++) {
			hashSet.add(permutation[i]);
			sum += permutation[i];
		}
		return hashSet.size() == permutation.length && sum == size * (size - 1) / 2;

	}


	/**
	 * Generates all cyclic permutation sets on n items. Each permutation set is represented
	 * as an ArrayList of <code>Integer[]</code>s. <br>
	 *     for example,
	 *     <br>
	 *     <code>generateCyclicPermutationSets(3)</code> will generate 2! sets of permutations,
	 *     where each permutation is of order 3, as given by: <br>
	 *     <code>{{[0,1,2], [1,2,0], [2,0,1] },<br> { [1,0,2], [0,2,1], [2,1,0]}}</code>.
	 * <br>
	 *     Algorithm taken from <i>The Art of Computer Programming 7.2.1.2, Algorithm C</i>.
	 * @param n positive integer.
	 * @return ArrayList of permutation sets.
	 */
	public static ArrayList<ArrayList<Integer[]>> generateCyclicPermutationSets(int n) {
		if(n <= 0) throw new IllegalArgumentException("Argument must be positive.");
		Integer[] id = new Integer[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
		}

		int k = n - 1;
		Integer[] copy = id.clone();
		ArrayList<ArrayList<Integer[]>> cycles = new ArrayList<ArrayList<Integer[]>>();

		while(k > 0){
			ArrayList<Integer[]> permSet = new ArrayList<Integer[]>();

			do{
				permSet.add(copy);
				k = n - 1;
				copy = shiftLeft(copy, k, 1);
			}
			while(copy[k] != id[k]);


			cycles.add(permSet);

			while(copy[k] == id[k] && k > 0){
				k--;
				copy = shiftLeft(copy, k, 1);
			}
		}

		return cycles;

	}

	/**
	 * Rotate the first k items of the array left, n places.
	 * @param array
	 * @param k
	 * @param n
	 * @return
	 */
	private static  Integer[] shiftLeft(Integer[] array, int k, int n){
		Integer[] arr = array.clone();
		while(n-- > 0) {
			int tmp = arr[0];
			for (int i = 0; i < k; i++) {
				arr[i] = arr[i + 1];
			}
			arr[k] = tmp;
		}
		return arr;
	}

	/**
	 *
	 * Rotate the first k items of the array right, n places.
	 * @param array
	 * @param k
	 * @param n
	 * @return
	 */
	private static  Integer[] shiftRight(Integer[] array, int k, int n){
		Integer[] arr = array.clone();
		while(n-- > 0) {
			int tmp = arr[k];
			for (int i = k; i > 0; i--) {
				arr[i] = arr[i - 1];
			}
			arr[0] = tmp;
		}
		return arr;
	}

}
