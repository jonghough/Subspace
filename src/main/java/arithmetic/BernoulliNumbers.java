package arithmetic;


import java.util.ArrayList;

/**
 * Bernoulli Number functions.
 */
public class BernoulliNumbers {

    /**
     * Generates the even <i>Bernoulli numbers</i>, from 0 to <code>n</code>.
     *
     * @param n
     * @return
     */
    public static ArrayList<Rational> generateBernoulliNumbers(int n) {
        int[] initialArray = new int[n + 2];
        ArrayList<Rational> bernoulliList = new ArrayList<>();
        bernoulliList.add(new Rational(1, 1));

        initialArray[1] = 1;
        boolean flag = true;
        int a = 1;
        int b = 1;
        int c = -2;
        int d = 1;
        for (int i = 0; i < 2 * n - 2; i++) {
            if (flag) {
                a += 1;
                b = 4 * b;
                c = -c;
                d = c * (b - 1);
                for (int k = a; k > 0; k--) {
                    initialArray[k] += initialArray[k + 1];
                }
            } else {
                for (int k = 1; k < a; k++) {
                    initialArray[k] += initialArray[k - 1];
                }
                bernoulliList.add(new Rational(initialArray[a - 1] * 1, d));
            }
            flag = !flag;
        }
        return bernoulliList;
    }

}
