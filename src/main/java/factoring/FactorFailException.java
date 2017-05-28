package factoring;

import java.math.BigInteger;

/**
 *
 */
public class FactorFailException extends RuntimeException{

    private BigInteger mFailedInt;
    private int mIterationTries;
    public FactorFailException(String msg, BigInteger N, int iterationTries){
        super(msg);
        mFailedInt = N;
        mIterationTries = iterationTries;
    }

    public int getTries(){
        return mIterationTries;
    }

    public BigInteger getFailedInt(){
        return mFailedInt;
    }
}
