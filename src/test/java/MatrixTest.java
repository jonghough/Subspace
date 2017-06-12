import static org.junit.Assert.*;

import linear.Matrix;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void test1() {
        Matrix m = new Matrix(3, 3, new double[]{80, -20, -20, -20, 40, -20, -20, -20, 130});
        Matrix n = m.inverse();
        Matrix mul = Matrix.matMul(m, n);

        assertTrue(Math.abs(mul.at(0, 0) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 1) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 2) - 1) < 0.000001f);


        assertTrue(Math.abs(mul.at(0, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(0, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 0) - 0) < 0.000001f);
    }


    @Test
    public void test2() {
        Matrix m = new Matrix(4, 4, new double[]{
                1, 2, -4, 0.3,
                6, 10, -12, -0.5,
                2, 3, 4, -13.5,
                6, -4, 0, 1
        });

        Matrix n = m.inverse();
        Matrix mul = Matrix.matMul(m, n);
        assertTrue(Math.abs(mul.at(0, 0) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 1) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 2) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(3, 3) - 1) < 0.000001f);


        assertTrue(Math.abs(mul.at(0, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(3, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 3) - 0) < 0.000001f);
    }


    @Test
    public void test3() {
        Matrix m = new Matrix(3, 3, new double[]{80, -20, -20, -20, 40, -20, -20, -20, 130});
        assertTrue("Determinant of 3x3 matrix is correct", m.det() == 300000.0);
    }

    @Test
    public void test4() {
        Matrix m = new Matrix(3, 4, new double[]{80, -20, -20, -20, 40, -20, -20, -20, 130, 10, 11, 12});
        System.out.println("m is " + m.print());
        Matrix n = new Matrix(4, 1, new double[]{10, 1, -3, 2});
        Matrix p = Matrix.matMul(m, n);
        assertTrue(p.at(0, 0) == 800);
        assertTrue(p.at(1, 0) == 400);
        assertTrue(p.at(2, 0) == 1301);
    }

    @Test
    public void test5() {
        Matrix m = new Matrix(3, 3, new double[]{-1, 1, 2, 0.5, -2, 0.3, 0.4, 1, 4});
        Matrix n = new Matrix(3, 3, new double[]{-1, 1, 2, 0.5, -2, 0.3, 0.4, 1, 4});
        Matrix p = Matrix.matMul(m, n);
        assertTrue(p.at(0, 0) == 2.3);
        assertTrue(p.at(1, 1) == 4.8);
        assertTrue(p.at(2, 2) == 17.1);

    }

    @Test
    public void test6() {
        Matrix m = new Matrix(5, 5, new double[]{
                0, 0.0, 0.3, 2, 1.774,
                6, 0.0, 7.5, 5, 3.3,
                0, 0.0, 1.11, 3, 0,
                12, 0.0, 0.5, 9, 0.87,
                2, 0.0, 2, 7, 1.1
        });

        double det = m.det();
        assertTrue(det == 0.0);

    }

    @Test
    public void test7() {
        Matrix m = new Matrix(3, 3, new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1});
        Matrix n = m.add(m);
        assertTrue(n.at(0, 0) == 2);
        assertTrue(n.at(0, 1) == 2);
        assertTrue(n.at(1, 2) == 2);
    }

    @Test
    public void test8() {
        Matrix m = new Matrix(3, 3, new double[]{1, 3, 0, 4, 5, 6, -1, 2, 0});
        Matrix n = m.add(m);
        System.out.println(n.print());
        assertTrue(n.at(0, 0) == 2);
        assertTrue(n.at(0, 1) == 6);
        assertTrue(n.at(1, 2) == 12);
        assertTrue(n.at(2, 2) == 0);
        assertTrue(n.at(2, 1) == 4);
    }

    @Test
    public void test9() {
        Matrix m = new Matrix(3, 3, new double[]{1, 3, 0, 4, 5, 6, -1, 2, 0});
        Matrix n = Matrix.matMul(m, m);
        System.out.println(m.print());
        System.out.println(n.print());
        assertTrue(n.at(0, 0) == 13);
        assertTrue(n.at(0, 1) == 18);
        assertTrue(n.at(1, 2) == 30);
        assertTrue(n.at(2, 2) == 12);
    }

    @Test
    public void test10() {
        Matrix m = new Matrix(3, 2, new double[]{1, 3, 0, 4, 5, 6});
        Matrix n = new Matrix(2, 3, new double[]{2, 3, -5, 3, 10, -1});
        Matrix p = Matrix.matMul(m, n);
        System.out.println(m.print());
        System.out.println(n.print());
        System.out.println(p.print());
        assertTrue(p.at(0, 0) == 11);
        assertTrue(p.at(0, 1) == 33);
        assertTrue(p.at(1, 2) == -4);
        assertTrue(p.at(2, 2) == -31);
    }


    @Test
    public void test11() {
        Matrix m = new Matrix(6, 6, new double[]{
                1, 20, -15, 4, 2.1, 7,
                11, 3, -3, 5, 17, 66,
                90, -1, 2, 7, 100, -95,
                2, 3, -1, 4, 12, 2,
                4, 4.1, 2.2, 1, 1, 0.5,
                1, 5, 3, 10, -10, 7
        });

        Matrix n = m.inverse();
        Matrix mul = Matrix.matMul(m, n);

        assertTrue(Math.abs(mul.at(0, 0) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 1) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 2) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(3, 3) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 4) - 1) < 0.000001f);
        assertTrue(Math.abs(mul.at(5, 5) - 1) < 0.000001f);


        assertTrue(Math.abs(mul.at(0, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(0, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(2, 1) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(1, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(3, 5) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(4, 5) - 0) < 0.000001f);
        assertTrue(Math.abs(mul.at(5, 0) - 0) < 0.000001f);

    }


    @Test
    public void test12() {
        Matrix m = new Matrix(10, 10, new double[]{
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 1
        });
        Matrix n = m.inverse();

        assertTrue(Math.abs(n.at(0, 0) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(1, 1) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(2, 2) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(3, 3) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(4, 4) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(5, 5) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(6, 6) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(7, 7) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(8, 8) - 1) < 0.000001f);
        assertTrue(Math.abs(n.at(9, 9) - 1) < 0.000001f);

        assertTrue(Math.abs(n.at(9, 0) + 1) < 0.000001f); // = -1

        assertTrue(Math.abs(n.at(1, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(2, 4) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(5, 3) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(8, 2) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(7, 8) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(0, 8) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(1, 9) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(6, 4) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(2, 3) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(5, 7) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(2, 6) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(8, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(4, 0) - 0) < 0.000001f);
        assertTrue(Math.abs(n.at(1, 7) - 0) < 0.000001f);
    }

}
