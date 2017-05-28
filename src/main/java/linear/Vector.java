package linear;


public class Vector extends Matrix{

    public Vector(int r, double[] data) {
        super(1,r, data);
    }

    public Vector(Vector v){
        this(v.cols(),v.flatten());
    }

    public static Vector zero(int c){
        return new Vector(c,new double[c]);
    }

}
