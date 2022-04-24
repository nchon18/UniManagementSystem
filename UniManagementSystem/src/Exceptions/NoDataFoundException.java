package Exceptions;

public class NoDataFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Couldn't find data corresponded to the input";
    }
}
