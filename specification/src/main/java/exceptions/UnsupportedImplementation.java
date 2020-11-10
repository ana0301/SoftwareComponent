package exceptions;

public class UnsupportedImplementation extends Exception{
    public UnsupportedImplementation(String implementation) {
        super(implementation+ " implementation is not supported. Please change implementation.");
    }
}
