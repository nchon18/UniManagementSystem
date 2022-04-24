package Exceptions;

public class InvalidUserCreationException extends Exception{
    @Override
    public String getMessage() {
        return "Couldn't create user. User with specified data already exists or " +
                "amount limitations have been reached";
    }
}
