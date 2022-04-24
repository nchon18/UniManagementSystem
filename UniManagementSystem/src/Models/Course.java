package Models;

import Exceptions.TooManyStudentsOnCourseException;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private static final int maxStudents=30;
    public final String name;
    public Lector assistant;
    public Lector lector;
    public List<Student> students;

    public Course(String name){
        this(name,null,null);
    }

    public Course(String name, Lector lector, Lector assistant){
        this.name=name;
        this.lector=lector;
        this.assistant=assistant;
        students=new ArrayList<>();
    }

    public void addStudent(Student student) throws TooManyStudentsOnCourseException {
        if(limitReached()){
            throw new TooManyStudentsOnCourseException();
        }else{
            students.add(student);
        }
    }

    public void deleteStudent(int id) {
        for(Student student:students){
            if(student.getId()==id){
                students.remove(student);
                return;
            }
        }
    }

    public void setLector(Lector lector){
        this.lector=lector;
    }

    public void setAssistant(Lector assistant){
        this.assistant=assistant;
    }

    public boolean limitReached(){ return students.size()==maxStudents; }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", assistant=" + assistant +
                ", lector=" + lector +
                ", students=" + students +
                '}';
    }
}
