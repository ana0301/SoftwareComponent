package exceptions;

public class SortUnable extends Exception{
    public SortUnable() {
        super("Unable to sort by nested entities");
    }
}
