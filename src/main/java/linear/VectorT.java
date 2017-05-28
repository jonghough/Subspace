package linear;

/**
 * Transposed vector class, of shape [N,1], i.e. N columns and 1 row.
 */
public class VectorT extends Matrix {

    public VectorT(int c, double[] data) {
        super(c, 1, data);
    }

    public VectorT(Vector v){
        this(v.cols(),v.flatten());
    }

    public static Vector zero(int c){
        return new Vector(c,new double[c]);
    }
}
