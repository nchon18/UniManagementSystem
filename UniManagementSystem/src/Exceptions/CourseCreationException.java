package Exceptions;

public class CourseCreationException extends Exception{
    @Override
    public String getMessage() {
        return "Couldn't create course. Limit for maximum courses has been reached or " +
                "course with the specified name already exists.";
    }
}
