package Exceptions;

public class TooManyStudentsOnCourseException extends Exception{
    @Override
    public String getMessage() {
        return "Couldn't add the student. The limit for maximum number of students has been reached.";
    }
}
