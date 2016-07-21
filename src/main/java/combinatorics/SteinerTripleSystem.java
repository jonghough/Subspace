package combinatorics;


import java.util.ArrayList;

/**
 * Generates elements of a <i>Steiner Triple System</i> of the given
 * order. The order must be congruent to 1 or 3 mod 6. <br>
 * For example, for STS(7), there are 7 elements, in 7 triples.
 */
public class SteinerTripleSystem {

    private ArrayList<Triple> mElements;
    private int mSize;
    private int mOrder;

    /**
     * Generates a <i>Steiner Triple System</i>, STS(n).
     * @param n
     */
    public SteinerTripleSystem(int n){
        mSize = n;
        mOrder = n % 6;
        if(mOrder != 1 && mOrder != 3){
            throw new IllegalArgumentException("Argument must be 1 or 3 mod 6.");

        }
        mElements = new ArrayList<>();
        if(mOrder == 1)
            generateMod1();
        else
            generateMod3();
    }

    /**
     * Generates the elements and triples, for the case when the number of elements is 3 mod 6.
     */
    private void generateMod3(){
        int res = (mSize - 3 ) / 6;
        int sz = 2 * res + 1;
        int mul = (sz + 1) / 2;
        for(int i = 0; i < sz;i++){
            Triple triple = new Triple(new Integer[]{i,0},new Integer[]{i,1},new Integer[]{i,2});
            mElements.add(triple);
        }
        for(int j = 0; j < sz -1; j++){
            for(int k = j +1; k < sz ; k++){
                Triple triple1 = new Triple(new Integer[]{j,0}, new Integer[]{k, 0}, new Integer[]{(mul * (j + k)) % sz, 1});
                Triple triple2 = new Triple(new Integer[]{j,1}, new Integer[]{k, 1}, new Integer[]{(mul * (j + k)) % sz, 2});
                Triple triple3 = new Triple(new Integer[]{j,2}, new Integer[]{k, 2}, new Integer[]{(mul * (j + k)) % sz, 0});
                mElements.add(triple1);
                mElements.add(triple2);
                mElements.add(triple3);
            }
        }
    }

    /**
     * Quasigroup function
     * @param left
     * @param right
     * @param max
     * @return
     */
    private int qgf(int left, int right, int max){
        int z = (left + right) % max;
        if(z % 2 == 0)
            z /= 2;
        else
            z = (z + max - 1) / 2;
        return z;

    }

    /**
     * Generates the elements and triples, for the case when the number of elements is 1 mod 6.
     */
    private void generateMod1(){
        int res = (mSize - 1 ) / 6;
        int sz = 2 * res + 1;
        int mul = (sz + 1) / 2;
        for(int i = 0; i < res; i++){
            Triple triple = new Triple(new Integer[]{i,0},new Integer[]{i,1},new Integer[]{i,2});
            mElements.add(triple);
        }
        for(int j = 0; j < 2 * res - 1; j++){
            for(int k = j + 1; k < 2 * res ; k++){
                int z = qgf(j,k,2 * res);
                Triple triple1 = new Triple(new Integer[]{j,0}, new Integer[]{k, 0}, new Integer[]{z, 1});
                Triple triple2 = new Triple(new Integer[]{j,1}, new Integer[]{k, 1}, new Integer[]{z, 2});
                Triple triple3 = new Triple(new Integer[]{j,2}, new Integer[]{k, 2}, new Integer[]{z, 0});
                mElements.add(triple1);
                mElements.add(triple2);
                mElements.add(triple3);
            }
        }
        for(int l = 0; l < res; l++){
            Triple triple1 = new Triple(new Integer[]{-1, -1}, new Integer[]{l + res, 0}, new Integer[]{l, 1});
            Triple triple2 = new Triple(new Integer[]{-1, -1}, new Integer[]{l + res, 1}, new Integer[]{l, 2});
            Triple triple3 = new Triple(new Integer[]{-1, -1}, new Integer[]{l + res, 2}, new Integer[]{l, 0});
            mElements.add(triple1);
            mElements.add(triple2);
            mElements.add(triple3);
        }
    }

    public ArrayList<Triple> getElements(){
        return mElements;
    }


    /**
     * Triple
     */
    public class Triple{
        public Integer[] element1;
        public Integer[] element2;
        public Integer[] element3;

        public Triple(Integer[] e1, Integer[] e2, Integer[] e3){
            element1 = e1;
            element2 = e2;
            element3 = e3;
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            for(int item : element1){
                builder.append(""+item+ " ");
            }
            builder.append("\n");
            for(int item : element2){
                builder.append(""+item+ " ");
            }
            builder.append("\n");
            for(int item : element3){
                builder.append(""+item+ " ");
            }
            builder.append("\n");

            return builder.toString();
        }
    }
}
