package Exceptions;

public class TooManyCoursesForStudentException extends Exception{

    @Override
    public String getMessage() {
        return "Couldn't add the course to the student. Maximum course limit has been reached";
    }
}
