package linear;


/**
 * Matrix class.
 */
public class Matrix {

    protected double[] mData;
    protected int mCols, mRows;

    public Matrix(int r, int c, double[] data) {
        if (data.length != c * r)
            throw new IllegalArgumentException("Number of data elements must match dimensions of matrix.");

        mData = data;
        mCols = c;
        mRows = r;

    }


    /**
     * Instantiate a matrix of <i>r</i> rows and <i>c</i> columns, and fill it with
     * elements of the given <i>r*c</i> length array.
     * @param r number of rows
     * @param c number of columns
     */
    public Matrix(int r, int c) {
        this(c, r, new double[c * r]);
    }

    /**
     * Copies the matrix and returns the copy object.
     * @return
     */
    public Matrix copy() {
        double[] d = new double[mData.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = mData[i];
        }
        return new Matrix(mRows, mCols, d);
    }

    /**
     * Retuns a 1-dimensional array contianing the elements of the matrix.
     * @return copy of the matrix contents
     */
    public double[] flatten() {
        return mData.clone();
    }

    /**
     * Creates a <i>rxc</i> matrix where each element is given the
     * value <i>v</i>.
     * @param r number of rows
     * @param c number of columns
     * @param v value to fill
     * @return matrix of shape <i>rxc</i>
     */
    public static Matrix fill(int r, int c, double v){
        double[] d = new double[c*r];
        for(int i = 0; i < c*r;i++){
            d[i] = v;
        }
        return new Matrix(c,r,d);
    }

    /**
     *
     * @param row
     * @return
     */
    public Matrix getRow(int row){
        double[] r = new double[cols()];
        for(int i = 0; i < cols(); i++){
            r[i] = at(row,i);
        }
        return new Matrix(rows(),1,r);
    }

    /**
     *
     * @param col
     * @return
     */
    public Matrix getColumn(int col){
        double[] r = new double[rows()];
        for(int i = 0; i < cols(); i++){
            r[i] = at(i, col);
        }
        return new Matrix(1, cols(),r);
    }

    /**
     *
     * @return
     */
    public int cols() {
        return mCols;
    }

    /**
     *
     * @return
     */
    public int rows() {
        return mRows;
    }

    /**
     *
     * @return
     */
    public String shapeStr() {
        return "(" + mRows + ", " + mCols + ")";
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public double at(int row, int col) {
        return mData[col + row * mCols];
    }

    /**
     *
     * @param row
     * @param col
     * @param value
     */
    public void setAt(int row, int col, double value) {
        mData[col + row * mCols] = value;
    }

    public static Matrix identity(int d) {
        double[] data = new double[d * d];
        for (int i = 0; i < data.length; i += d + 1) {
            data[i] = 1;
        }
        return new Matrix(d, d, data);
    }

    /**
     * In place element-wise scalar multiplication.
     * @param d double to multiply each element by.
     */
    public void mul(double d){
        for(int i = 0; i < mData.length;i++){
            mData[i] *= d;
        }
    }

    /**
     *
     * @param R
     * @return
     */
    public Matrix add(Matrix R) {
        Matrix L = this;
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot add two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.cols(); i++) {
            for (int j = 0; j < L.rows(); j++) {
                data[j + i * L.cols()] = L.at(i, j) + R.at(i, j);
            }
        }
        return new Matrix(L.rows(), L.cols(), data);
    }

    /**
     *
     * @param R
     * @return
     */
    public Matrix sub( Matrix R) {
        Matrix L = this;
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot subtract with two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < L.cols(); j++) {
                data[i + j * L.rows()] = L.at(i, j) - R.at(i, j);
            }
        }
        return new Matrix(L.rows(), L.cols(), data);
    }

    /**
     *
     * @param R
     * @return
     */
    public Matrix mul(Matrix R) {
        Matrix L = this;
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot multiply with two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < L.cols(); j++) {
                data[i + j * L.rows()] = L.at(i, j) * R.at(i, j);
            }
        }
        return new Matrix(L.rows(), L.cols(), data);
    }

    /**
     *
     * @param R
     * @param allowDBZ
     * @return
     */
    public Matrix div(Matrix R, boolean allowDBZ) {
        Matrix L = this;
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot divide with two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];

        if (allowDBZ) {
            for (int i = 0; i < L.rows(); i++) {
                for (int j = 0; j < L.cols(); j++) {
                    double s = R.at(i, j);
                    if (Math.abs(s) < 0.00000001) {
                        if (s >= 0)
                            s = Double.POSITIVE_INFINITY;
                        else
                            s = Double.NEGATIVE_INFINITY;
                        data[i + j * L.rows()] = s;
                    } else {
                        data[i + j * L.rows()] = L.at(i, j) / s;
                    }
                }
            }
        } else {
            for (int i = 0; i < L.rows(); i++) {
                for (int j = 0; j < L.cols(); j++) {
                    double s = R.at(i, j);
                    if (Math.abs(s) < 0.00000001) {
                        throw new MatrixAlgorithmException("Denominator value is too close to zero. Cannot perform matrix division.");
                    } else {
                        data[i + j * L.rows()] = L.at(i, j) / s;
                    }
                }
            }
        }

        return new Matrix(L.rows(), L.cols(), data);
    }


    /**
     * Performs matrix multiplication
     *
     * @param L
     * @param R
     * @return
     */
    public static Matrix matMul(Matrix L, Matrix R) {
        if (L.cols() != R.rows())
            throw new IllegalArgumentException("Matrix multiplication fail. Cannot multiply matrix of shape" +
                    L.shapeStr() + " with matrix of shape " + R.shapeStr() + ".");
        double[] data = new double[L.rows() * R.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < R.cols(); j++) {
                double v = 0;
                for (int k = 0; k < L.cols(); k++) {
                    double cd = L.at(i,k) * R.at(k,j);
                    v += cd;
                }
                data[j + i * R.cols()] = v;
            }
        }

        return new Matrix(L.rows(), R.cols(), data);
    }

    /**
     *
     * @return
     */
    private double diagMul(){
        if(rows() != cols()) throw new MatrixAlgorithmException("Cannot multiply along diagonal of non-square matrix.");
        double prod = 1;
        for(int i = 0; i < cols(); i++){
            prod *= at(i,i);
        }
        return prod;
    }

    /**
     *
     * @return
     */
    private double trace(){
        if(rows() != cols()) throw new MatrixAlgorithmException("Cannot calculate trace of non-square matrix.");
        double tr = 0;
        for(int i = 0; i < cols(); i++){
            tr += at(i,i);
        }
        return tr;
    }

    /**
     *
     * @return
     */
    public double det(){
        Matrix[] lup = lu();
        Matrix L = lup[0];
        Matrix U = lup[1];
        double d = L.diagMul() * U.diagMul();
        if(d != d) return 0;
        return d;
    }

    /**
     *
     * @return
     */
    public  Matrix transpose() {
        Matrix M = copy();
        double[] data = new double[M.rows() * M.cols()];
        for (int i = 0; i < M.cols(); i++) {
            for (int j = 0; j < M.rows(); j++) {
                data[i + j * M.rows()] = M.mData[j + i * M.cols()];
            }
        }
        return new Matrix(M.rows(), M.cols(), data);
    }

    /**
     *
     * @return
     */
    private Matrix pivot() {
        int n = rows();
        Matrix id = identity(n);

        for (int i = 0; i < n; i++) {
            double maxm = at(i,i);
            int row = i;
            for (int j = i; j < n; j++)
                if (at(i,j) > maxm) {
                    maxm = at(i,j);
                    row = j;
                }

            if (i != row) {
                for(int j = 0; j < cols();j++) {
                    double tmp = id.at(i,j);
                    id.setAt(i,j,id.at(row,j));
                    id.setAt(row,j,tmp);
                }

            }
        }
        return id;
    }

    /**
     *
     * @return
     */
    private Matrix[] lu( ) {
         Matrix A = copy();
        int n = rows();
        Matrix L = new Matrix(n,n);
         Matrix U = new Matrix(n,n);
         Matrix P = pivot();
        Matrix A2 = Matrix.matMul(A,P);

        for (int j = 0; j < n; j++) {
            L.setAt(j,j,1);
            for (int i = 0; i < j + 1; i++) {
                double s1 = 0;
                for (int k = 0; k < i; k++) {
                    s1 += U.at(k, j) * L.at(i, k);
                }

                U.setAt(i,j,A2.at(i,j) - s1);
            }

            for (int i = j; i < n; i++) {
                double s2 = 0;
                for (int k = 0; k < j; k++)
                    s2 += U.at(k,j) * L.at(i,k);
                L.setAt(i,j,(A2.at(i,j) - s2) / U.at(j,j));
            }
        }

        return new Matrix[]{L, U, P};
    }


    /**
     * Returns the matrix inverse, if this matrix is
     * invertible.
     * @return inverse of this instance.
     */
    public Matrix inverse(){

        Matrix[] decomp = lu();
        Matrix LI = invertLowerTriangular(decomp[0]);
        Matrix UI = invertUpperTriangular(decomp[1]);
        Matrix P = (decomp[2]);
        return matMul(P, matMul(UI,LI));
    }

    /**
     *
     * @param T
     * @return
     */
    private static Matrix invertLowerTriangular(Matrix T){
        Matrix C =  T.copy();
        for(int k = 0; k < T.rows();k++){
            C.setAt(k,k, 1.0 / T.at(k,k));
            for(int i = k+1; i <T.rows();i++){
                double m = 0;
                for(int a = k; a < i;a++){
                    m+= T.at(i,a) * C.at(a,k);
                }
                 m /= T.at(i,i);
                C.setAt(i,k,-1.0*m);
            }
        }
        return C;
    }

    /**
     *
     * @param T
     * @return
     */
    private static Matrix invertUpperTriangular(Matrix T){
        Matrix V = T.transpose();
        Matrix C = V.copy();
        for(int k = 0; k < V.rows();k++){
            C.setAt(k,k, 1.0 / V.at(k,k));
            for(int i = k+1; i <V.rows();i++){
                double m = 0;
                for(int a = k; a < i;a++){
                    m+= V.at(i,a) * C.at(a,k);
                }
                m /= V.at(i,i);
                C.setAt(i,k,-1.0*m);
            }
        }
        return C.transpose();
    }


    /**
     *
     * @return
     */
    public String print() {
        String p = "\n";
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                p += "\t" + at(i, j);
            }
            p += "\n";
        }
        return p;
    }
}
