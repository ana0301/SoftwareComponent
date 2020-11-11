package exceptions;

public class UnsupportedImplementation extends Exception{
    public UnsupportedImplementation(String implementation) {
        super("Only "+ implementation+ " files are supported. Please select file.");
    }
}
