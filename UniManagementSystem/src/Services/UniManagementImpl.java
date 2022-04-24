package Services;

import Exceptions.*;
import Models.Course;
import Models.Lector;
import Models.LectorType;
import Models.Student;

import java.util.ArrayList;
import java.util.List;

public class UniManagementImpl implements UniManagement{

    public static final int MAX_STUDENTS=1000;
    public static final int MAX_COURSES=10;

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Lector> lectors;
    private final List<Lector> assistants;

    public UniManagementImpl(){
        students=new ArrayList<>();
        courses=new ArrayList<>();
        lectors=new ArrayList<>();
        assistants=new ArrayList<>();
    }


    @Override
    public Course createCourse(String courseName) throws CourseCreationException {
        if(courses.size()==MAX_COURSES||findCourse(courseName)!=null){
            throw new CourseCreationException();
        }else{
            Course course=new Course(courseName);
            courses.add(course);
            return course;
        }
    }

    @Override
    public boolean deleteCourse(String courseName) throws NoDataFoundException {
        Course course=findCourse(courseName);
        if(course==null){
            throw new NoDataFoundException();
        }else{
            courses.remove(course);
            course.lector.deleteCourse(courseName);
            course.assistant.deleteCourse(courseName);
            for(Student student:course.students){
                student.deleteCourse(courseName);
            }
            return true;
        }
    }

    @Override
    public Student createStudent(int id, String firstName, String lastName, String facNumber) throws InvalidUserCreationException {
        if(students.size()==MAX_STUDENTS||findStudent(id)!=null){
            throw new InvalidUserCreationException();
        }else{
            Student student=new Student(id,firstName,lastName, facNumber);
            students.add(student);
            return student;
        }
    }

    @Override
    public boolean deleteStudent(int id) throws NoDataFoundException {
        Student student=findStudent(id);
        if(student==null){
            throw new NoDataFoundException();
        }else{
            students.remove(student);
            for(Course course:student.courses){
                course.deleteStudent(id);
            }
            return true;
        }
    }

    @Override
    public Lector createAssistance(int id, String firstName, String lastName) throws InvalidUserCreationException {
        if(findAssistant(id)!=null){
            throw new InvalidUserCreationException();
        }else{
            Lector assistant=new Lector(id,firstName,lastName, LectorType.ASSISTANT);
            assistants.add(assistant);
            return assistant;
        }
    }

    @Override
    public boolean deleteAssistance(int id) throws NoDataFoundException {
        Lector assistant=findAssistant(id);
        if(assistant==null){
            throw new NoDataFoundException();
        }else{
            assistants.remove(assistant);
            for(Course course:assistant.courses){
                course.setAssistant(new Lector());
            }
            return true;
        }
    }

    @Override
    public boolean asighAssistanceToCourse(Lector assistance, Course course) {
        try {
            assistance.addCourse(course);
            course.setAssistant(assistance);
            return true;
        } catch (TooManyCoursesForLectorException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean asighProfessorToCourse(Lector professor, Course course) {
        try {
            professor.addCourse(course);
            course.setLector(professor);
            return true;
        } catch (TooManyCoursesForLectorException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        if(!student.limitReached()&&!course.limitReached()){
            try {
                student.addCourse(course);
                course.addStudent(student);
                return true;
            } catch (TooManyCoursesForStudentException | TooManyStudentsOnCourseException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean addStudentsToCourse(Student[] students, Course course) {
        boolean allAdded=true;
        for(Student student:students){
            if(!addStudentToCourse(student,course)){
                System.out.println("Couldn't assign a student to the course");
                allAdded=false;
            }
        }
        return allAdded;
    }

    @Override
    public void removeStudentFromCourse(Student student, Course course) {
        student.deleteCourse(course.name);
        course.deleteStudent(student.getId());
    }

    @Override
    public Lector createLector(int id, String firstName, String lastName, LectorType type) throws InvalidUserCreationException {
        if(type==LectorType.NONE)
            return null;
        if(findLector(id)!=null){
            throw new InvalidUserCreationException();
        }else{
            Lector lector=new Lector(id,firstName,lastName, type);
            lectors.add(lector);
            return lector;
        }
    }

    @Override
    public boolean deleteLector(int id) throws NoDataFoundException {
        Lector lector=findLector(id);
        if(lector==null){
            throw new NoDataFoundException();
        }else{
            lectors.remove(lector);
            for(Course course:lector.courses){
                course.setLector(new Lector());
            }
            return true;
        }
    }

    public Course findCourse(String name){
        for(Course course:courses){
            if(course.name.equals(name))
                return course;
        }
        return null;
    }

    public Student findStudent(int id){
        for(Student student:students){
            if(student.getId()==id)
                return student;
        }
        return null;
    }

    public Lector findLector(int id){
        for(Lector lector:lectors){
            if(lector.getId()==id)
                return lector;
        }
        return null;
    }

    public Lector findAssistant(int id){
        for(Lector assistant:assistants){
            if(assistant.getId()==id)
                return assistant;
        }
        return null;
    }
}
