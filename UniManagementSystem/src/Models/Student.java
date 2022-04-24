package Models;

import Exceptions.TooManyCoursesForStudentException;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{

    public List<Course> courses;
    public String facNumber;
    private static final int maxCourses=10;

    public Student(int id, String firstName, String lastName,String facNumber) {
        super(id, firstName, lastName);
        courses=new ArrayList<>();
        this.facNumber=facNumber;
    }

    public void addCourse(Course course) throws TooManyCoursesForStudentException {
        if(limitReached()){
            throw new TooManyCoursesForStudentException();
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
        return "Student{" +
                ", facNumber='" + facNumber + '\'' +
                '}';
    }
}
