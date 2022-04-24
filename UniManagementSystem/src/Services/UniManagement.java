package Services;

import Exceptions.CourseCreationException;
import Exceptions.InvalidUserCreationException;
import Exceptions.NoDataFoundException;
import Models.Course;
import Models.Lector;
import Models.LectorType;
import Models.Student;

public interface UniManagement {
    /**
     * Crete a new course with name courseName and return it
     *
     * @return new instance of course with the passed name if it's created or
     * null in another case. A course will be created only if already does not exists
     * another course with the same courseName
     */

    public Course createCourse(String courseName) throws CourseCreationException;



    /**
     * Delete a course with name courseName
     *
     * @return <code>true</code> only in case the course with passed name was removed
     */

    public boolean deleteCourse(String courseName) throws NoDataFoundException;


    /**
     * Creat and return new instance of Student with the passed arguments and initial state of the student
     *
     * @return new instance of a student identified with the passed ID, if already does not exists,
     * and the other arguments as initial state if it's cerated or
     * null in another case
     */

    public Student createStudent(int id, String firstName, String lastName, String facNumber) throws InvalidUserCreationException;


    /**
     * Delete a student with the passed ID
     *
     * @return <code>true</code> only in cae the student was remvoed
     */

    public boolean deleteStudent(int id) throws NoDataFoundException;


    /**
     * Create a new assistance in the univertity withthe passed arguemtns as initial state
     *
     * @return new created professor assistance identified withthe passed ID, if already does not exists with that ID
     */

    public Lector createAssistance(int id, String firstName, String lastName) throws InvalidUserCreationException;


    /**
     * Delete an professor asisstance with the passed ID, if such exists
     *
     * @return <code>true</code> ONLY in case the assistance was removed
     */

    public boolean deleteAssistance(int id) throws NoDataFoundException;


    /**
     * Aighn an assistance to a course
     *
     * @return <code>true</code> ONLY n case the assistance was succesfully assighed to the course
     */

    public boolean asighAssistanceToCourse(Lector assistance, Course course);


    /**
     * Aighn a professor to a course
     *
     * @return <code>true</code> ONLY n case the professor was succesfully assighed to the course
     */

    public boolean asighProfessorToCourse(Lector professor, Course course);


    /**
     * Add a studnt to a course
     *
     * @return <code.true</code> ONLY inca se the student is successfully added to the course
     */

    public boolean addStudentToCourse(Student student, Course course);


    /**
     * Add all students to a course
     *
     * @return <code>true</code> ONLY inc ase all students are added to a course
     */

    public boolean addStudentsToCourse(Student[] students, Course course);


    /**
     * Remvoe a student from a course
     */
    public void removeStudentFromCourse(Student student, Course course);

    public Lector createLector(int id, String firstName, String lastName, LectorType type) throws InvalidUserCreationException;

    public boolean deleteLector(int id) throws NoDataFoundException;
}
