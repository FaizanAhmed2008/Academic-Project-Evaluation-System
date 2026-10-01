import java.io.Serializable;

/*
 * Result.java
 * This class represents the final evaluation result.
 * It holds all the result data that will be displayed on the Result Screen.
 */
public class Result implements Serializable {
    
    private Student student;
    private Evaluation evaluation;
    
    // Default constructor
    public Result() {
        this.student = new Student();
        this.evaluation = new Evaluation();
    }
    
    // Parameterized constructor
    public Result(Student student, Evaluation evaluation) {
        this.student = student;
        this.evaluation = evaluation;
    }
    
    // Getter and Setter for Student
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    // Getter and Setter for Evaluation
    public Evaluation getEvaluation() {
        return evaluation;
    }
    
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
    
    /*
     * Method to reset result
     */
    public void reset() {
        if (student != null) {
            student.reset();
        }
        if (evaluation != null) {
            evaluation.reset();
        }
    }
}