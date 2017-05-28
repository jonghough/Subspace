package linear;


public class Matrix {

    private double[] mData;
    private int mCols, mRows;

    public Matrix(int c, int r, double[] data) {
        if (data.length != c * r)
            throw new IllegalArgumentException("Number of data elements must match dimensions of matrix.");

        mData = data;
        mCols = c;
        mRows = r;

    }


    public Matrix(int c, int r) {
        this(c, r, new double[c * r]);
    }

    public Matrix copy() {
        double[] d = new double[mData.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = mData[i];
        }
        return new Matrix(mCols, mRows, d);
    }

    public double[] flatten() {
        double[] flat = new double[cols() * rows()];
        for (int i = 0; i < cols() * rows(); i++) {
            flat[i] = mData[i];
        }
        return flat;
    }

    public static Matrix fill(int c, int r, double v){
        double[] d = new double[c*r];
        for(int i = 0; i < c*r;i++){
            d[i] = v;
        }
        return new Matrix(c,r,d);
    }

    public Matrix getRow(int row){
        double[] r = new double[cols()];
        for(int i = 0; i < cols(); i++){
            r[i] = at(i,row);
        }
        return new Matrix(cols(),1,r);
    }

    public Matrix getColumn(int col){
        double[] r = new double[rows()];
        for(int i = 0; i < cols(); i++){
            r[i] = at(col,i);
        }
        return new Matrix(1, rows(),r);
    }


    public int cols() {
        return mCols;
    }

    public int rows() {
        return mRows;
    }

    public String shapeStr() {
        return "(" + mCols + ", " + mRows + ")";
    }

    public double at(int col, int row) {
        return mData[row + col * mRows];
    }

    public void setAt(int col, int row, double value) {
        mData[row + col * mRows] = value;
    }

    public static Matrix identity(int d) {
        double[] data = new double[d * d];
        for (int i = 0; i < data.length; i += d + 1) {
            data[i] = 1;
        }
        return new Matrix(d, d, data);
    }

    public Matrix add(Matrix L, Matrix R) {
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot add two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < L.cols(); j++) {
                data[i + j * L.rows()] = L.at(i, j) + R.at(i, j);
            }
        }
        return new Matrix(L.cols(), L.rows(), data);
    }

    public Matrix sub(Matrix L, Matrix R) {
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot subtract with two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < L.cols(); j++) {
                data[i + j * L.rows()] = L.at(i, j) - R.at(i, j);
            }
        }
        return new Matrix(L.cols(), L.rows(), data);
    }

    public Matrix mul(Matrix L, Matrix R) {
        if (L.rows() != R.rows() || L.cols() != R.cols()) {
            throw new IllegalArgumentException("Cannot multiply with two matrices of different shapes.");
        }
        double[] data = new double[L.rows() * L.cols()];
        for (int i = 0; i < L.rows(); i++) {
            for (int j = 0; j < L.cols(); j++) {
                data[i + j * L.rows()] = L.at(i, j) * R.at(i, j);
            }
        }
        return new Matrix(L.cols(), L.rows(), data);
    }

    public Matrix div(Matrix L, Matrix R, boolean allowDBZ) {
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

        return new Matrix(L.cols(), L.rows(), data);
    }


    /**
     * Performs matrix multiplication
     *
     * @param L
     * @param R
     * @return
     */
    public static Matrix matMul(Matrix L, Matrix R) {
        if (L.rows() != R.cols())
            throw new IllegalArgumentException("Matrix multiplication fail. Cannot multiply matrix of shape" +
                    L.shapeStr() + " with matrix of shape " + R.shapeStr() + ".");
        double[] data = new double[L.cols() * R.rows()];
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

        return new Matrix(L.cols(), R.rows(), data);
    }

    private double diagMul(){
        if(rows() != cols()) throw new MatrixAlgorithmException("Cannot multiply along diagonal of non-square matrix.");
        double prod = 1;
        for(int i = 0; i < cols(); i++){
            prod *= at(i,i);
        }
        return prod;
    }

    private double trace(){
        if(rows() != cols()) throw new MatrixAlgorithmException("Cannot calculate trace of non-square matrix.");
        double tr = 0;
        for(int i = 0; i < cols(); i++){
            tr += at(i,i);
        }
        return tr;
    }

    public double det(){
        Matrix[] lup = lu();
        Matrix L = lup[0];
        Matrix U = lup[1];
        double d = L.diagMul() * U.diagMul();
        System.out.println("DET IS "+d);
        return d;
    }

    /**
     * @param M
     * @return
     */
    public static Matrix transpose(Matrix M) {
        double[] data = new double[M.rows() * M.cols()];
        for (int i = 0; i < M.cols(); i++) {
            for (int j = 0; j < M.rows(); j++) {
                data[i + j * M.rows()] = M.mData[j + i * M.cols()];
            }
        }
        return new Matrix(M.rows(), M.cols(), data);
    }


    private void rowAdd(int row1, int row2, double val) {
        for (int i = 0; i < mCols; i++) {
            setAt(i, row1, at(i, row1) + val * at(i, row2));
        }
    }

    private void rowMul(int row, double val) {
        for (int i = 0; i < mCols; i++) {
            setAt(i, row, at(i, row) * val);
        }
    }

    private void rowSwap(int row1, int row2) {

        for (int i = 0; i < mCols; i++) {
            double tmp = at(i, row1);
            setAt(i, row1, at(i, row2));
            setAt(i, row2, tmp);

        }
    }



    private Matrix pivotize() {
        int n = rows();
       // Matrix C = copy();
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

    private Matrix[] lu( ) {
         Matrix A = copy();
        int n = rows();
        Matrix L = new Matrix(n,n);
         Matrix U = new Matrix(n,n);
         Matrix P = pivotize();
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

        System.out.println("A2: "+A2.print());
        System.out.println("L: "+L.print());
        System.out.println("U: "+U.print());
        System.out.println("P: "+P.print());
        System.out.println("L*U: "+matMul(L,U).print());
        return new Matrix[]{L, U, P};
    }


    public Matrix inverse(){
        Matrix[] decomp = lu();
        Matrix LI = invertLowerTriangular(decomp[0]);
        Matrix UI = invertUpperTriangular(decomp[1]);

        System.out.println("LI: "+LI.print());
        System.out.println("UI: "+UI.print());
        return matMul(UI,LI);
    }

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

    private static Matrix invertUpperTriangular(Matrix T){
        Matrix V = transpose(T);
        System.out.println("V:  "+V.print());
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
        return transpose(C);
    }


    public String print() {
        String p = "\n";
        for (int i = 0; i < mCols; i++) {
            for (int j = 0; j < mRows; j++) {
                p += "\t" + at(i, j);
            }
            p += "\n";
        }
        return p;
    }
}
