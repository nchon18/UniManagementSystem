package Services;

import Exceptions.CourseCreationException;
import Exceptions.InvalidUserCreationException;
import Exceptions.NoDataFoundException;
import Models.Course;
import Models.Lector;
import Models.LectorType;
import Models.Student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static UniManagementImpl uniManagement;
    public static boolean flag=true;

    public static void main(String[] args) {
        uniManagement=new UniManagementImpl();
        InputParser parser=new InputParser();
        while(flag){
            try{
                BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
                String command=reader.readLine();
                parser.executeCommand(command);
            }catch (Exception e){
                System.out.println("Invalid input");
            }
        }
    }

    public static void createStudent(String id, String firstname, String lastname, String facNumber){
        try {
            uniManagement.createStudent(Integer.parseInt(id),firstname,lastname,facNumber);
        } catch (InvalidUserCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createCourse(String name){
        try {
            uniManagement.createCourse(name);
        } catch (CourseCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAssistant(String id, String firstname, String lastname){
        try {
            uniManagement.createAssistance(Integer.parseInt(id),firstname,lastname);
        } catch (InvalidUserCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createProfessor(String id, String firstname, String lastname, String lectorType){
        LectorType type=LectorType.NONE;
        lectorType=lectorType.toUpperCase();
        switch (lectorType) {
            case "DOCENT" -> type = LectorType.DOCENT;
            case "PROFESSOR" -> type = LectorType.PROFESSOR;
        }
        try {
            uniManagement.createLector(Integer.parseInt(id),firstname,lastname,type);
        } catch (InvalidUserCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void assignStudentToCourse(String id,String courseName){
        Student student=uniManagement.findStudent(Integer.parseInt(id));
        Course course=uniManagement.findCourse(courseName);
        if(student==null||course==null){
            System.out.println("Invalid input");
            return;
        }
        uniManagement.addStudentToCourse(student,course);
    }

    public static void assignAssistantToCourse(String id,String courseName){
        Lector assistant =uniManagement.findAssistant(Integer.parseInt(id));
        Course course=uniManagement.findCourse(courseName);
        if(assistant==null||course==null){
            System.out.println("Invalid input");
            return;
        }
        uniManagement.asighAssistanceToCourse(assistant,course);
    }

    public static void assignProfessorToCourse(String id, String courseName){
        Lector lector=uniManagement.findLector(Integer.parseInt(id));
        Course course=uniManagement.findCourse(courseName);
        if(lector==null||course==null){
            System.out.println("Invalid input");
            return;
        }
        uniManagement.asighProfessorToCourse(lector,course);
    }

    public static void deleteCourse(String name){
        try {
            uniManagement.deleteCourse(name);
        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudent(String id){
        try {
            uniManagement.deleteStudent(Integer.parseInt(id));
        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAssistant(String id){
        try {
            uniManagement.deleteAssistance(Integer.parseInt(id));
        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteLector(String id){
        try {
            uniManagement.deleteLector(Integer.parseInt(id));
        } catch (NoDataFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeStudentFromCourse(String id,String name){
        Student student=uniManagement.findStudent(Integer.parseInt(id));
        Course course=uniManagement.findCourse(name);
        if(student==null||course==null){
            System.out.println("Invalid input");
            return;
        }
        uniManagement.removeStudentFromCourse(student,course);
    }

    public static void addStudentsToCourse(List<String> tokens){
        String courseName=tokens.get(1);
        for(int i=2;i<tokens.size();i++){
            assignStudentToCourse(tokens.get(i),courseName);
        }
    }

}

class InputParser{

    private static final String CREATE_STUDENT="createStudent";
    private static final String CREATE_COURSE="createCourse";
    private static final String CREATE_ASSISTANCE="createAssistance";
    private static final String CREATE_PROFESSOR="createProfessor";
    private static final String ASIGH_STUDENT_TO_COURSE="asighStudentToCourse";
    private static final String ASIGH_ASSISTANCE_TO_COURSE="asighAssistanceToCourse";
    private static final String ASIGH_PROFESSOR_TO_COURSE="asighProfessorToCourse";
    private static final String DELETE_COURSE="deleteCourse";
    private static final String DELETE_STUDENT="deleteStudent";
    private static final String DELETE_ASSISTANCE="deleteAssistance";
    private static final String DELETE_LECTOR="deleteLector";
    private static final String ADD_STUDENTS_TO_COURSE="addStudentsToCourse";
    private static final String REMOVE_STUDENT_FROM_COURSE="removeStudentFromCourse";
    private static final String EXIT="exit";

    public void executeCommand(String command){
        List<String> tokens=new ArrayList<>();
        StringTokenizer tokenizer=new StringTokenizer(command," ");
        while(tokenizer.hasMoreTokens()){
            tokens.add(tokenizer.nextToken());
        }
        redirect(tokens);
    }

    private void redirect(List<String> tokens){
        String start=tokens.get(0);
        switch (start) {
            case CREATE_STUDENT -> Main.createStudent(tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4));
            case CREATE_COURSE -> Main.createCourse(tokens.get(1));
            case CREATE_ASSISTANCE -> Main.createAssistant(tokens.get(1), tokens.get(2), tokens.get(3));
            case CREATE_PROFESSOR -> Main.createProfessor(tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4));
            case ASIGH_STUDENT_TO_COURSE -> Main.assignStudentToCourse(tokens.get(1), tokens.get(2));
            case ASIGH_ASSISTANCE_TO_COURSE -> Main.assignAssistantToCourse(tokens.get(1), tokens.get(2));
            case ASIGH_PROFESSOR_TO_COURSE -> Main.assignProfessorToCourse(tokens.get(1), tokens.get(2));
            case DELETE_COURSE -> Main.deleteCourse(tokens.get(1));
            case DELETE_ASSISTANCE -> Main.deleteAssistant(tokens.get(1));
            case DELETE_STUDENT -> Main.deleteStudent(tokens.get(1));
            case DELETE_LECTOR -> Main.deleteLector(tokens.get(1));
            case ADD_STUDENTS_TO_COURSE -> Main.addStudentsToCourse(tokens);
            case REMOVE_STUDENT_FROM_COURSE -> Main.removeStudentFromCourse(tokens.get(1), tokens.get(2));
            case EXIT -> Main.flag = false;
            default -> System.out.println("Invalid input");
        }
    }
}
