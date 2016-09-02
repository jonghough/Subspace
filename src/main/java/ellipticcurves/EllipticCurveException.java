package ellipticcurves;


import java.math.BigInteger;

public class EllipticCurveException extends ArithmeticException {

    public BigInteger exceptionPoint;

    public EllipticCurveException(String message, BigInteger p) {
        super(message);
        exceptionPoint = p;
    }
}
