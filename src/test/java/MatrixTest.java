import static org.junit.Assert.*;

import linear.Matrix;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void test1(){
        Matrix m = new Matrix(3,3, new double[]{80, -20,-20,-20,40,-20,-20,-20,130});  //new double[]{1,2,-4, 0.3, 6, 10, -12, -0.5, 2,3,4,-13.5,6,-4,0,1});
        System.out.println("PRE MATRIX IS "+m.print());
        System.out.println("MATRIX IS  "+m.inverse().print());
    }


    @Test
    public void test2(){
        Matrix m = new Matrix(4,4, new double[]{1,2,-4, 0.3, 6, 10, -12, -0.5, 2,3,4,-13.5,6,-4,0,1});
        System.out.println("PRE MATRIX IS "+m.print());
        System.out.println("MATRIX IS  "+m.inverse().print());
    }


    @Test
    public void test3(){
        Matrix m = new Matrix(3,3, new double[]{80, -20,-20,-20,40,-20,-20,-20,130});
        assertTrue("Determinant of 3x3 matrix is correct", m.det() == 300000.0);
    }

    @Test
    public void test4(){
        Matrix m = new Matrix(3,4, new double[]{80, -20,-20,-20,40,-20,-20,-20,130,10,11,12});
        System.out.println("m is "+m.print());
    }

    @Test
    public void test5(){
        Matrix m = new Matrix(3,3, new double[]{-1,1,2,0.5,-2,0.3,0.4,1,4});
        Matrix n = new Matrix(3,3, new double[]{-1,1,2,0.5,-2,0.3,0.4,1,4});
        System.out.println("Mat mul =  "+Matrix.matMul(m,n).print());
    }

    @Test
    public void test6(){
        Matrix m = new Matrix(5,5, new double[]{
                0,  10.5,  0.3,  2,  1.774,
                6,  3,     7.5,  5,    3.3,
                0,  0.1,   1.11, 3,    0,
                12, 0.5,    0.5, 9,    0.87,
                2,  33,     2,   7,    1.1
        });
        System.out.println("PRE MATRIX IS "+m.print());
        System.out.println("MATRIX IS  "+m.inverse().print());
    }
}
