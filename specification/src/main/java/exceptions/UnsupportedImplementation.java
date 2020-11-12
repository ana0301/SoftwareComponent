package exceptions;

/**
 * Type of exception that throws when chosen implementation is not supported
 */
public class UnsupportedImplementation extends Exception{
    public UnsupportedImplementation(String implementation) {
        super("Only "+ implementation+ " files are supported. Please select file.");
    }
}
