import complex.Cpx;
import numerics.FFT;
import org.junit.Test;

import static numerics.FFT.*;


//--------------------------------------------------
//        Under Construction
//--------------------------------------------------

public class FFTTest {

    @Test
    public void test1(){

        double[] real = {0,0,0.5,1};//{0, 0.7071, 1, 0.7071, 0, -0.7071, -1, -0.7071};//{0.26440,0.84081,1.36150,1.61282, 1.36672, 0.71697,0.07909, -0.14576};
        double[] im = {0.6,1,0.25,0};//{0,0,0,0,0,0,0,0};
        fft2X(4,2,real,im);

        for(double i : real){
            System.out.println("RRRRR +++++++++++++++++>>>>   "+i);
        }

        for(double i : im){
            System.out.println("IIIII +++++++++++++++++>>>>   "+i);
        }


        double[] data1 = {0, 0.7071, 1, 0.7071, 0, -0.7071, -1, -0.7071};//real.clone();
        double[] data2 = {0, 0.7071, 1, 0.7071, 0, -0.7071, -1, -0.7071};//real.clone();

        double[] f1 = new double[16];
        double[] f2 = new double[16];

        twofft(data1, data2, f1, f2);

        for(double f : f1){
            System.out.println("F1 DATA +++++++++++++++++>>>>   "+f);
        }

        for(double f : f2){
            System.out.println("F2 DATA +++++++++++++++++>>>>   "+f);
        }


    }

    @Test
    public void test2(){
        /*

        nm.fft.fft([0+0.6j,0+1j,0.5+0.25j,1+0.3j, 0.1+0.2j,0.55+1j,0.5+0.44j, 0.88+1j])
array([ 3.53000000+4.79j      , -1.25873629+1.19903066j,
       -0.20000000+1.44j      ,  0.06878680+0.20908117j,
       -1.33000000-1.81j      ,  0.67873629-0.39903066j,
       -1.60000000-1.22j      ,  0.11121320+0.59091883j])
         */
        double[] d = {0.3,0.6,0,1,0.5,0.25,1,0.3, 0.1,0.2,0.55,1,0.5,0.44,0.88,1};//{0, 0, 0.7071, 0, 1, 0, 0.7071, 0, 0,0, -0.7071,0, -1,0, -0.7071,0};//
        d = fft1X(d, 1);

        for(double i : d){
            System.out.println("transformed +++++++++++++++++>>>>   "+i);
        }

        // -----

        /*
        nm.fft.fft([0.26440+0j, 0.84081+0j, 1.36150+0j, 1.61282+0j, 1.36672+0j, 0.71697+0j, 0.07909+0j, -0.14576+0j])
array([ 6.09655000+0.j        , -2.25825574-2.61348195j,
        0.19053000-0.09072j   ,  0.05361574-0.04866195j,
        0.04687000+0.j        ,  0.05361574+0.04866195j,
        0.19053000+0.09072j   , -2.25825574+2.61348195j])
         */
        double[] dd =  {0.26440,0,  0.84081,0,  1.36150,0,   1.61282,0,   1.36672,0,   0.71697,0,   0.07909,0,   -0.14576,0};
        dd = fft1X(dd, 1);

        for(double i : dd){
            System.out.println("transformed2 +++++++++++++++++>>>>   "+i);
        }
    }

    @Test
    public void testrealfft(){
       /* nm.fft.rfft([0,1,0.5,1,0.5,1,0.25,0.75,0,1.5,0,1,0.5,0.25,1,0])
        array([ 9.25000000+0.j        , -0.55797851-0.61180381j,
                -1.00000000-1.01776695j, -0.66932812+0.23281939j,
                -0.75000000-1.j        , -1.09843884-0.120734j  ,
                -1.00000000-2.51776695j,  2.32574547-0.9653572j , -3.75000000+0.j        ])  */


        double[] data = {0,1,0.5,1,0.5,1,0.25,0.75,0,1.5,0,1,0.5,0.25,1,0};
        Cpx[] datacpx = realFFTX(data);

        for(Cpx f : datacpx){
            System.out.println("data realfft  "+f);
        }
    }

    @Test
    public void testrealfft2(){


        double[] data = {0,1,2,3,4,5,6,7};
        Cpx[] datacpx = realFFTX(data);

        for(Cpx f : datacpx){
            System.out.println("data realfft  "+f);
        }
    }
}
