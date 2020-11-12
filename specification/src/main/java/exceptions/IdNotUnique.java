package exceptions;

/**
 * Type of exception that throws when the forwarded id is not unique
 */
public class IdNotUnique extends Exception{

    public IdNotUnique() {
        super("ID Entity is not unique");
    }
}
