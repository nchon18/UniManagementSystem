package Exceptions;

public class TooManyCoursesForLectorException extends Exception{
    @Override
    public String getMessage() {
        return "Couldn't assign lector to the course. Limit for maximum courses has been reached.";
    }
}
