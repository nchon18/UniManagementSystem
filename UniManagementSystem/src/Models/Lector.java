package Models;

import Exceptions.TooManyCoursesForLectorException;

import java.util.ArrayList;
import java.util.List;

public class Lector extends User{

    private final LectorType type;
    public List<Course> courses;
    private static final int maxCourses=4;

    public Lector(int id, String firstName, String lastName,LectorType type) {
        super(id, firstName, lastName);
        this.type=type;
        courses=new ArrayList<>();
    }

    public Lector(){
        this(-1,"","",LectorType.NONE);
    }

    public void addCourse(Course course) throws TooManyCoursesForLectorException {
        if(limitReached()){
            throw new TooManyCoursesForLectorException();
        }else{
            courses.add(course);
        }
    }

    public void deleteCourse(String name){
        for(Course course:courses){
            if(course.name.equals(name)){
                courses.remove(course);
                return;
            }
        }
    }

    @Override
    public boolean limitReached() {
        return courses.size()==maxCourses;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "type=" + type +
                '}';
    }
}
