package exceptions;

/**
 * Type of exception that throws when a chosen parameter to sort is a nested entity
 */
public class SortUnable extends Exception{
    public SortUnable() {
        super("Unable to sort by nested entities");
    }
}
