package numerics;


//--------------------------------------------------
//        Under Construction
//--------------------------------------------------

/**
 * Implementation of the <i>Fast Fourier Transform</i> algorithm.
 */
public class FFT {

    /**
     * Implementation of <i>Fast Fourier Transform</i>, using decimation in time.
     * Algorithm is from <br>
     *     <i>Numerical Recipes p.~ 508.</i>
     *
     * @param data
     * @param isign
     */
    public static double[] fft1X(double[] data, int isign){
        long mmax, m, istep;
        double wtemp, wr, wpr, wpi, wi, theta;
        double tempr, tempi;


        // Numerical Recipes starts all indices at 1!
        final int n = data.length;
        final int nn = n / 2;
        int j = 1;
        for(int i = 1; i < n; i+=2){
            int jj = j - 1;
            int ii = i - 1;
            if(j > i){
                double t = data[jj];
                data[jj] = data[ii];
                data[ii] = t;

                t = data[jj+1];
                data[jj+1] = data[ii+1];
                data[jj+1] = t;
            }
            m = nn;
            while(m >= 2 && j > m){
                j-=m;
                m >>= 1;

            }
            j += m;
        }

        mmax = 2;
        while(n > mmax){
            istep = mmax << 1;
            theta = isign * (Math.PI * 2.0 / mmax);
            wtemp = Math.sin(0.5 * theta);
            wpr = -2.0 * wtemp * wtemp;
            wpi = Math.sin(theta);
            wr = 1.0;
            wi = 0.0;
            for(m=1; m < mmax; m+=2){
                int mm = (int)m - 1;
                for(int i = (int)m; i <=n; i+= istep){
                    int ii = i - 1;
                    j = i + (int)mmax;
                    int jj = j - 1;
                    tempr = wr * data[jj] - wi * data[jj+1];
                    tempi = wr * data[jj+1] + wi * data[jj];
                    data[jj] = data[ii] - tempr;
                    data[jj+1] = data[ii+1] - tempi;
                    data[ii] = data[ii] + tempr;
                    data[ii+1] = data[ii+1] + tempi;
                 }
                wr = (wtemp = wr) * wpr - wi * wpi + wr;
                wi = wi * wpr + wtemp*wpi + wi;
            }
            mmax *= 2;
        }
        return data;
    }


    public static void realfft(double[] data, int isign){

    }


    /**
     *
     * @param data1
     * @param data2
     * @param fft1
     * @param fft2
     */
    public static void twofft(double[] data1, double[] data2, double fft1[], double fft2[]){
        int n = data1.length;
        int n2,n3;
        n3 = 1 + (n2 = 2 + n + n);
        for(int j = 0, jj = 0; j < n; j++, jj+=2){
            //int rj = j - 1;
            //int rjj = jj - 1;
            fft1[jj] = data1[j];
            fft1[jj+1] = data2[j];
        }

        fft1X(fft1,-1);
        fft2[0] = fft1[1];
        fft1[1] = fft2[1] = 0.0;
        for(int j = 3;j <= n; j+=2){
            int jj = j - 1;

            double rep = 0.5 * (fft1[jj] + fft1[n2 - j - 1]);
            double rem = 0.5 * (fft1[j] - fft1[n2 - j - 1]);
            double aip = 0.5 * (fft1[j + 1] + fft1[n3 - j - 1]);
            double aim = 0.5 * (fft1[j + 1] + fft1[n3 - j - 1]);

            fft1[jj] = rep;
            fft1[jj+1] = aim;
            fft1[n2 - j-1] =rep;
            fft1[n3 - j-1] = -aim;

            fft2[jj] = aip;
            fft2[jj+1] = -rem;
            fft2[n2 - j-1] = aip;
            fft2[n3 - j-1] = rem;
        }
    }




    /**
     * Implementation of the <i>Decimation in Frequency - Sande-Tukey FFT algorithm</i>
     *
     * @param N
     * @param p
     * @param x
     * @param y
     */
    public static void fft2X(int N /* 2^p */, int p, double[] x, double[] y){ //num analysis bk
        int m = p;
        int N2 = x.length;

        for(int k = 1; k <= p; k++){
            int N1 = N2;
            N2 >>= 1;
            double angle = 0.0;
            double arg = 2 * Math.PI / N1;
            for(int j = 0; j < N2; j++) {
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
        for(int i = 0; i < N - 2; i++){
            if(i < j){
                double xt = x[j];
                x[j] = x[i];
                x[i] = xt;

                double yt = y[j];
                y[j] = y[i];
                y[i] = yt;


            }
            int k = N / 2;
            while( k < j + 1) {
                // if( k >= j + 1) break;
                j = j - k;
                k = k / 2;
            }
            j = j + k;

        }

        for(int i = 0; i < N - 1;i++){
            x[i] = x[i];// / N;
            y[i] = y[i];// / N;
        }

    }
}
