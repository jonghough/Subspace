package combinatorics;

/**
 *
 */
public class CatalanNumbers {

    private long catalanNumber(int index){
        if(index < 0) throw new IllegalArgumentException("Argument must be non-negative.");

        long com = Binomial.coefficient(2 * index , index);
        return com / (index + 1);
    }

    /**
     *
     */
    private int mIndex;

    /**
     *
     */
    private long mCatalanNumber;

    /**
     *
     * @param index
     */
    public CatalanNumbers(int index){
        if(index < 0) throw new IllegalArgumentException("Argument must be non-negative.");
        mIndex = index;
        mCatalanNumber = catalanNumber(index);
    }

    public long getCatalanNumber(){
        return mCatalanNumber;
    }


    /**
     * Utility function for calculating Catalan ranks.
     * @param x x value, first parameter of the utility function
     * @param y y value, second parameter of the utility function
     * @return values used for calculating ranks.
     */
    private int M(int x, int y){
        if( x < 0 || x > 2 * mIndex) throw new IllegalArgumentException("x argument out of range.");
        if( y < 0) throw new IllegalArgumentException("y argument out of range.");
        if( x + y > 2 * mIndex) return 0;
        if((x + y) % 2 == 1) return 0;
        long f = Binomial.coefficient(2 * mIndex - x, mIndex - (x + y) / 2);
        long s = Binomial.coefficient(2 * mIndex - x, mIndex - 1 - (x + y) / 2);

        return (int)(f - s);
    }

    /**
     * For this instance's <i>Catalan number</i>, this method will calculate the catalan rank
     * of a given permutation.
     * <br>
     * For example, if <br><br>
     *     <code>
     *         catalanNumbers cn = new CatalanNumbers(4);
     *     </code><br>
     *     <code>
     *         cn.catalanRank(new boolean[]{false, false, false, false, true, true, true, true});
     *     </code><br>
     *     will return <br>
     *         0, because this is the rank of the <i>Catalan family</i> (00001111), represented by the given boolean array.
     * @param catalanSet
     * @return the rank of the given boolean array.
     */
    public int catalanRank(boolean[] catalanSet){
        if (catalanSet.length != 2 * mIndex) throw new IllegalArgumentException("Array length is not correct value.");
        int f = 0;
        int lower = 0;
        int i = 0;
        while(i < 2 * mIndex - 2){
            if(catalanSet[i] == false) {
                f++;
            }
            else{
                lower = lower + M(i + 1, f + 1);
                f--;
            }
            i++;
        }
        return lower;
    }
}
