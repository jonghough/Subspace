package numerics;


//--------------------------------------------------
//        Under Construction
//--------------------------------------------------

import complex.Cpx;

/**
 * Implementation of the <i>Fast Fourier Transform</i> algorithm.
 */
public class FFT {

    /**
     * Implementation of <i>Fast Fourier Transform</i>, using decimation in time.
     * Algorithm is from <br>
     * <i>Numerical Recipes in C : The Art of Scientifc Computing, p.~ 508.</i>
     *
     * @param data
     * @param isign
     */
    public static double[] fft1X(double[] data, int isign) {

        int m;
        double wtemp, wr, wpr, wpi, wi, theta;
        double tempr, tempi;


        // Numerical Recipes starts all indices at 1!

        final int N = data.length;
        final int halfN = N >> 1;
        int j = 1;
        for (int i = 1; i < N; i += 2) {
            if (j > i) {
                int in = i - 1;
                int jn = j - 1;
                double t = data[jn];
                data[jn] = data[in];
                data[in] = t;

                t = data[jn + 1];
                data[jn + 1] = data[in + 1];
                data[in + 1] = t;
            }
            m = halfN;
            while (m >= 1 && j > m) {
                j -= m;
                m >>= 1;

            }
            j += m;
        }

        int k = 2;
        int step;
        while (N > k) {
            step = k << 1;
            theta = isign * (Math.PI * 2.0 / k);
            wtemp = Math.sin(0.5 * theta);
            wpr = -2.0 * wtemp * wtemp;
            wpi = Math.sin(theta);
            wr = 1.0;
            wi = 0.0;
            for (m = 0; m < k; m += 2) {
                for (int i = m; i < N - 1; i += step) {
                    j = i + k;
                    tempr = wr * data[j] - wi * data[j + 1];
                    tempi = wr * data[j + 1] + wi * data[j];
                    data[j] = data[i] - tempr;
                    data[j + 1] = data[i + 1] - tempi;
                    data[i] += tempr;
                    data[i + 1] += tempi;
                }
                wr = (wtemp = wr) * wpr - wi * wpi + wr;
                wi = wi * wpr + wtemp * wpi + wi;
            }
            k = step;
        }

        return data;
    }


    /**
     * Calculates the <i>Discrete Fourier Transform</i> on the given
     * data array of real valued items. The size of the array must be a power
     * of two.
     * @param data array of real values
     * @param isign forward or inverse trandform.
     */
    public static void realfft(double[] data, int isign) {
        int n = data.length;
        double c1 = 0.5, c2;
        double theta = Math.PI / (double) (n >> 1);
        if (isign == 1) {
            c2 = -0.5;
            data = fft1X(data, 1);
        } else {
            c2 = 0.5;
            theta *= -1;
        }
        double wtemp = Math.sin(0.5 * theta);
        double wpr = -2.0 * wtemp * wtemp;
        double wpi = Math.sin(theta);
        double wr = 1.0 + wpr;
        double wi = wpi;
        int np3 = n + 3;
        int i1, i2, i3, i4;
        double h1r, h1i, h2r, h2i;
        for (int i = 2; i <= (n >> 2); i++) {
            i4 = 1 + (i3 = np3 - (i2 = 1 + (i1 = i + i - 1)));
            h1r = c1 * (data[i1 - 1] + data[i3 - 1]);
            h1i = c1 * (data[i2 - 1] - data[i4 - 1]);

            h2r = -c2 * (data[i2 - 1] + data[i4 - 1]);
            h2i = c2 * (data[i1 - 1] - data[i3 - 1]);
            data[i1 - 1] = h1r + wr * h2r - wi * h2i;
            data[i2 - 1] = h1i + wr * h2i + wi * h2r;
            data[i3 - 1] = h1r - wr * h2r + wi * h2i;
            data[i4 - 1] = -h1i + wr * h2i + wi * h2r;

            wr = (wtemp = wr) * wpr - wi * wpi + wr;
            wi = wi * wpr + wtemp * wpi + wi;
        }
        if (isign == 1) {
            data[0] = (h1r = data[0]) + data[1];
            data[1] = h1r - data[1];
        } else {
            data[0] = c1 * ((h1r = data[1]) + data[1]);
            data[1] = c1 * (h1r - data[1]);
            fft1X(data, -1);
        }
    }

    /**
     * @param data
     * @return
     */
    public static Cpx[] realFFTX(double[] data) {
        realfft(data, 1);

        int len = 1 + (data.length >> 1);
        Cpx[] res = new Cpx[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                res[i] = new Cpx(data[0], 0);
            } else if (i == len - 1) {
                res[i] = new Cpx(data[1], 0);
            } else {
                res[i] = new Cpx(data[i << 1], -data[(i << 1) + 1]);
            }
        }
        return res;
    }


    /**
     * @param data1
     * @param data2
     * @param fft1
     * @param fft2
     */
    public static void twofft(double[] data1, double[] data2, double fft1[], double fft2[]) {
        int n = data1.length;
        int n2, n3;
        n3 = 1 + (n2 = 2 + n + n);
        for (int j = 0, jj = 0; j < n; j++, jj += 2) {
            //int rj = j - 1;
            //int rjj = jj - 1;
            fft1[jj] = data1[j];
            fft1[jj + 1] = data2[j];
        }

        fft1X(fft1, -1);
        fft2[0] = fft1[1];
        fft1[1] = fft2[1] = 0.0;
        for (int j = 3; j <= n; j += 2) {
            int jj = j - 1;

            double rep = 0.5 * (fft1[jj] + fft1[n2 - j - 1]);
            double rem = 0.5 * (fft1[j] - fft1[n2 - j - 1]);
            double aip = 0.5 * (fft1[j + 1] + fft1[n3 - j - 1]);
            double aim = 0.5 * (fft1[j + 1] + fft1[n3 - j - 1]);

            fft1[jj] = rep;
            fft1[jj + 1] = aim;
            fft1[n2 - j - 1] = rep;
            fft1[n3 - j - 1] = -aim;

            fft2[jj] = aip;
            fft2[jj + 1] = -rem;
            fft2[n2 - j - 1] = aip;
            fft2[n3 - j - 1] = rem;
        }
    }


    public static void cosft(double[] data, int n) {

    }


    /**
     * Implementation of the <i>Decimation in Frequency - Sande-Tukey FFT algorithm</i>
     * Not from Numerical Recipes
     * @param N
     * @param p
     * @param x
     * @param y
     */
    public static void fft2X(int N /* 2^p */, int p, double[] x, double[] y) { //num analysis bk
        int m = p;
        int N2 = x.length;

        for (int k = 1; k <= p; k++) {
            int N1 = N2;
            N2 >>= 1;
            double angle = 0.0;
            double arg = 2 * Math.PI / N1;
            for (int j = 0; j < N2; j++) {
                double c = Math.cos(angle);
                double s = -Math.sin(angle);
                for (int i = j; i < N; i += N1) {
                    int kk = i + N2;
                    double xt = x[i] - x[kk];
                    x[i] = x[i] + x[kk];
                    double yt = y[i] - y[kk];
                    y[i] = y[i] + y[kk];
                    x[kk] = xt * c - yt * s;
                    y[kk] = yt * c + xt * s;
                }
                angle = (j + 1) * arg;
            }
        }

        int j = 0;
        for (int i = 0; i < N - 2; i++) {
            if (i < j) {
                double xt = x[j];
                x[j] = x[i];
                x[i] = xt;

                double yt = y[j];
                y[j] = y[i];
                y[i] = yt;


            }
            int k = N / 2;
            while (k < j + 1) {
                j = j - k;
                k = k / 2;
            }
            j = j + k;

        }

        for (int i = 0; i < N - 1; i++) {
            x[i] = x[i];// / N;
            y[i] = y[i];// / N;
        }

    }


    /**
     * Calcualtes sin transform of real valued points in the given
     * array. Size of array ms tbe a power of two.
     * @param data
     */
    public static void sinft(double[] data){
        double theta = Math.PI / data.length;
        int n = data.length;
        double wtemp = Math.sin(0.5 * theta);
        double wpr = -2.0 * wtemp * wtemp;
        double wpi = Math.sin(theta);
        double wr = 1.0, wi = 0.0;
        data[0] = 0.0;
        double y1, y2;
        for(int j = 2; j <= (data.length / 2)+1;j++){
            int jn = j - 1;
            wr = (wtemp = wr) * wpr - wi * wpi + wr;
            wi = wi * wpr + wtemp * wpi + wi;
            y1 = wi * (data[jn] + data[n - jn]);
            y2 = 0.5 * (data[jn] - data[n - jn]);
            data[jn] = y1 + y2;
            data[n - jn] = y1 - y2;
        }
        realfft(data,1);

        data[0] *= 0.5;
        double sum = data[1] = 0;
        for(int j = 1; j <= n - 1; j+=2){

            sum += data[j - 1];
            data[j-1] = data[j];
            data[j] = sum;
        }
    }

    /**
     * Calculates sin transform of real valued points in the given
     * array. Size of array ms tbe a power of two.
     * @param data
     */
    public static void cosft(double[] data){
        double theta = Math.PI / data.length;
        int n = data.length;
        double wtemp = Math.sin(0.5 * theta);
        double wpr = -2.0 * wtemp * wtemp;
        double wpi = Math.sin(theta);
        double wr = 1.0, wi = 0.0;
       // data[0] = 0.0;
        double y1, y2;
        for(int j = 2; j <= (data.length >> 1)+1;j++){
            wr = (wtemp = wr)*wpr - wi * wpi + wr;
            wi = wi * wpr + wtemp * wpi + wi;
            y1 = wi * (data[j - 1] + data[n + 2 - (j - 1)]);
            y2 = 0.4 * (data[j - 1] - data[n+2 - (j - 1)]);
            data[j - 1] = y1 + y2;
            data[n + 2 - (j - 1)] = y1 - y2;
        }
        realfft(data,1);
        data[0] *= 0.5;
        double sum = 0;
        for(int j = 1; j <= n - 1; j+=2){
            sum += data[j - 1];
            data[j-1] = data[j];
            data[j] = sum;
        }
    }
}
