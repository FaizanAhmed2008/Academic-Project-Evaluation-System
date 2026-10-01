import java.io.Serializable;

/*
 * Evaluation.java
 * This class handles the project evaluation process.
 * It stores marks for each evaluation criterion, validates marks,
 * calculates total marks, percentage, grade and status.
 */
public class Evaluation implements Serializable {
    
    // Maximum marks for each criterion as per project requirements
    public static final int MAX_PROJECT_IDEA = 10;
    public static final int MAX_IMPLEMENTATION = 20;
    public static final int MAX_DOCUMENTATION = 20;
    public static final int MAX_PRESENTATION = 20;
    public static final int MAX_VIVA = 20;
    public static final int MAX_INNOVATION = 10;
    
    // Total maximum marks
    public static final int TOTAL_MAX_MARKS = 100;
    
    // Passing criteria
    public static final int PASSING_MARKS = 50;
    
    // Marks for each criterion
    private double projectIdea;
    private double implementation;
    private double documentation;
    private double presentation;
    private double viva;
    private double innovation;
    
    // Feedback from evaluator
    private String feedback;
    
    // Calculated results
    private double totalMarks;
    private double percentage;
    private String grade;
    private String status; // PASS or FAIL
    
    // Default constructor
    public Evaluation() {
        this.projectIdea = 0;
        this.implementation = 0;
        this.documentation = 0;
        this.presentation = 0;
        this.viva = 0;
        this.innovation = 0;
        this.feedback = "";
        this.totalMarks = 0;
        this.percentage = 0;
        this.grade = "F";
        this.status = "FAIL";
    }
    
    // Getter and Setter methods
    
    public double getProjectIdea() {
        return projectIdea;
    }
    
    public void setProjectIdea(double projectIdea) {
        this.projectIdea = projectIdea;
    }
    
    public double getImplementation() {
        return implementation;
    }
    
    public void setImplementation(double implementation) {
        this.implementation = implementation;
    }
    
    public double getDocumentation() {
        return documentation;
    }
    
    public void setDocumentation(double documentation) {
        this.documentation = documentation;
    }
    
    public double getPresentation() {
        return presentation;
    }
    
    public void setPresentation(double presentation) {
        this.presentation = presentation;
    }
    
    public double getViva() {
        return viva;
    }
    
    public void setViva(double viva) {
        this.viva = viva;
    }
    
    public double getInnovation() {
        return innovation;
    }
    
    public void setInnovation(double innovation) {
        this.innovation = innovation;
    }
    
    public String getFeedback() {
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    // Getter methods for calculated results
    public double getTotalMarks() {
        return totalMarks;
    }
    
    public double getPercentage() {
        return percentage;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public String getStatus() {
        return status;
    }
    
    /*
     * Method to validate if marks entered are within allowed limits
     * Returns true if all marks are valid, else false
     */
    public boolean validateMarks() {
        boolean isValid = true;
        
        // Validate Project Idea (0 - 10)
        if (projectIdea < 0 || projectIdea > MAX_PROJECT_IDEA) {
            isValid = false;
        }
        
        // Validate Implementation (0 - 20)
        if (implementation < 0 || implementation > MAX_IMPLEMENTATION) {
            isValid = false;
        }
        
        // Validate Documentation (0 - 20)
        if (documentation < 0 || documentation > MAX_DOCUMENTATION) {
            isValid = false;
        }
        
        // Validate Presentation (0 - 20)
        if (presentation < 0 || presentation > MAX_PRESENTATION) {
            isValid = false;
        }
        
        // Validate Viva (0 - 20)
        if (viva < 0 || viva > MAX_VIVA) {
            isValid = false;
        }
        
        // Validate Innovation (0 - 10)
        if (innovation < 0 || innovation > MAX_INNOVATION) {
            isValid = false;
        }
        
        return isValid;
    }
    
    /*
     * Method to calculate total marks, percentage, grade and status
     * This method should be called after validating marks
     */
    public void calculateResults() {
        // Calculate total marks
        totalMarks = projectIdea + implementation + documentation + 
                     presentation + viva + innovation;
        
        // Calculate percentage
        percentage = (totalMarks / (double) TOTAL_MAX_MARKS) * 100.0;
        
        // Determine grade based on percentage
        if (percentage >= 90.0) {
            grade = "A+";
        } else if (percentage >= 80.0) {
            grade = "A";
        } else if (percentage >= 70.0) {
            grade = "B";
        } else if (percentage >= 60.0) {
            grade = "C";
        } else if (percentage >= 50.0) {
            grade = "D";
        } else {
            // Below 50
            grade = "F";
        }
        
        // Determine status (PASS or FAIL)
        if (totalMarks >= PASSING_MARKS) {
            status = "PASS";
        } else {
            status = "FAIL";
        }
    }
    
    /*
     * Method to reset all evaluation data
     */
    public void reset() {
        this.projectIdea = 0;
        this.implementation = 0;
        this.documentation = 0;
        this.presentation = 0;
        this.viva = 0;
        this.innovation = 0;
        this.feedback = "";
        this.totalMarks = 0;
        this.percentage = 0;
        this.grade = "F";
        this.status = "FAIL";
    }
}