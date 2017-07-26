
package arithmetic;

import java.util.ArrayList;

/**
 *
 */
public class ContinuedFractions {


    /**
     * Calculates the continued fraction from the array terms.
     * 
     * @param terms
     * @return
     */
    public static float calculateContinuedFraction(int[] terms) {
        if (terms == null || terms.length == 0) {
            throw new IllegalArgumentException("Bad argument");
        }
        float cf = terms[terms.length - 1];
        for (int i = terms.length - 2; i >= 0; i--) {
            cf = terms[i] + 1.0f / cf;
        }
        return cf;
    }


    /**
     * Computes the continued fraction expansion of the square root of the
     * integer <i>N</i>.
     * @param N
     * @param limit
     * @return
     */
    public static ArrayList<Integer> sqrtToCF(int N, int limit){
        ArrayList<Integer> xs = new ArrayList<>();
        double sqrtN = Math.sqrt(N);
        int xNow = (int)sqrtN;
        int x0 = xNow;
        int fNow = 1;
        int fNext;
        int mNow = 0;
        int mNext;
        xs.add(xNow);
        for(int i = 0; i < limit;i++){
             mNext = fNow * xNow - mNow;
            fNext = (N - mNext*mNext) / fNow;
            int xNext = (x0+mNext) / fNext;
            xs.add(xNext);
            mNow = mNext;
            fNow = fNext;
            xNow = xNext;
        }
        return xs;
    }


    public static ArrayList<Integer> realToCF(double D, int limit){
        ArrayList<Integer> as = new ArrayList<>();
        int ip = (int)D;
        if(ip == D){
            as.add(ip);
            return as;
        }

        int aNow = ip;
        double tNow = D - aNow;
        double tNext;
        int aNext;
        as.add(aNow);
        for(int i = 0; i < limit; i++){
            double rec = 1.0 / tNow;
            aNext = (int)rec;
            tNext = rec - aNext;
            as.add(aNext);
            tNow = tNext;
        }
        return as;

    }
}
