import java.io.Serializable;

/*
 * Student.java
 * This class represents student details for the project evaluation.
 * It stores information about the student and their project.
 */
public class Student implements Serializable {
    
    private String studentName;
    private String rollNumber;
    private String projectName;
    private String guideName;
    
    // Default constructor
    public Student() {
        this.studentName = "";
        this.rollNumber = "";
        this.projectName = "";
        this.guideName = "";
    }
    
    // Parameterized constructor
    public Student(String studentName, String rollNumber, String projectName, String guideName) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.projectName = projectName;
        this.guideName = guideName;
    }
    
    // Getter and Setter for Student Name
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    // Getter and Setter for Roll Number
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    // Getter and Setter for Project Name
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    // Getter and Setter for Guide Name
    public String getGuideName() {
        return guideName;
    }
    
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
    
    /*
     * Method to reset all student details
     */
    public void reset() {
        this.studentName = "";
        this.rollNumber = "";
        this.projectName = "";
        this.guideName = "";
    }
}