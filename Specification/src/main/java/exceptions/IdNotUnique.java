package exceptions;

public class IdNotUnique extends Exception{
    public IdNotUnique() {
        super("ID Entity is not unique");
    }
}
