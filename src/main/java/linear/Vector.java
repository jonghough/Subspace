package linear;


import java.util.ArrayList;
import java.util.List;

public class Vector extends Matrix{

    private DotProduct mDotProduct;

    public Vector(int r, double[] data) {
        super(r,1, data);
    }

    public Vector(Vector v){
        this(v.rows(),v.flatten());
    }

    public static Vector zero(int r){
        return new Vector(r,new double[r]);
    }

    public double dot(Vector v){
        if(v.flatten().length != this.flatten().length){
            throw new IllegalArgumentException("Cannot perform dot product on vectors of different length.");
        }
        return mDotProduct.product(this,v);
    }

    public double magnitude(){
        return Math.sqrt(mDotProduct.product(this,this));
    }

    public void normalize(){

        double mg = magnitude();
        if(Math.abs(mg) < 0.0000001f){
            throw new RuntimeException("COuld not normalize a matrix. Magnitude is too small.");
        }
        for(int i = 0; i < mData.length; i++){
            this.setAt(i,0,at(i,0)/mg);
        }
    }


    public Vector project(Vector v){
        // multiplication is in-place so must
        // copy the matrix.
        Vector c = (Vector)v.copy();
        double mag = c.magnitude();
        double dp = dot(c);

        c.mul(dp/mag);
        return c;
    }

    @Override
    public VectorT transpose(){
        return new VectorT(mRows,mData);
    }


    /**
     *
     * @param vectorList
     * @return
     */
    public static List<Vector> orthonormalize(List<Vector> vectorList){
        if(vectorList == null || vectorList.size() == 0){
            return new ArrayList<Vector>();
        }
        List<Vector> orthoList = new ArrayList<Vector>();
        Vector first = vectorList.get(0);
        Vector fc = (Vector)first.copy();
        fc.normalize();
        int size = first.rows();
        orthoList.add(fc);
        Vector sum = Vector.zero(size);
        Vector prev = fc;
        for(int i = 1; i < vectorList.size();i++){
            if(vectorList.get(i).rows() != size){
                throw new RuntimeException("Cannot orthonormalize vectors of different sizes.");
            }
            sum = (Vector)sum.add(vectorList.get(i).project(prev));
            Vector v = (Vector)vectorList.get(i).sub(sum);
            v.normalize();
            orthoList.add(v);
            prev = vectorList.get(i);

        }
        return orthoList;
    }


}
