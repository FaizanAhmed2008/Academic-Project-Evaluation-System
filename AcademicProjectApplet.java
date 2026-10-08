import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/*
 * AcademicProjectApplet.java
 * Main class for Academic Project Evaluation System.
 *
 * Note: Java Applets (java.applet.Applet) were removed from the JDK after
 * Java 8, so this project uses a plain AWT Frame. The code is the same idea:
 * one window, four panels, and CardLayout to move between them.
 *
 * Application flow:
 * Login -> Student Details -> Project Evaluation -> Result
 */
public class AcademicProjectApplet extends Frame implements ActionListener {

    // Layout manager used to switch between the four screens
    private CardLayout cardLayout;

    // We need a normal container to hold the four panels
    private Container mainContainer;

    // One panel for each screen
    private Panel loginPanel;
    private Panel studentDetailsPanel;
    private Panel evaluationPanel;
    private Panel resultPanel;

    // ---- Login screen components ----
    private Label lblLoginTitle;
    private Label lblUsername;
    private Label lblPassword;
    private Label lblLoginError;
    private TextField txtUsername;
    private TextField txtPassword;
    private Button btnLogin;

    // ---- Student details screen components ----
    private Label lblStudentTitle;
    private TextField txtStudentName;
    private TextField txtRollNumber;
    private TextField txtProjectName;
    private TextField txtGuideName;
    private Button btnNext;

    // ---- Evaluation screen components ----
    private Label lblProjectIdea;
    private TextField txtProjectIdea;
    private TextField txtImplementation;
    private TextField txtDocumentation;
    private TextField txtPresentation;
    private TextField txtViva;
    private TextField txtInnovation;
    private TextArea txtFeedback;
    private Label lblEvalError;
    private Button btnEvaluate;

    // ---- Result screen components ----
    private Label lblResultName;
    private Label lblResultRoll;
    private Label lblResultProject;
    private Label lblResultTotal;
    private Label lblResultPercentage;
    private Label lblResultGrade;
    private Label lblResultStatus;
    private Label lblResultMessage;
    private TextArea txtFeedbackResult;
    private Button btnSaveCSV;
    private Button btnNewEvaluation;

    // Data objects (one object for each model class)
    private Login loginObj;
    private Student studentObj;
    private Evaluation evaluationObj;
    private Result resultObj;

    // Used to print the percentage neatly (for example 82.50)
    private DecimalFormat decimalFormat;

    /*
     * Constructor: builds the whole window.
     * We use BorderLayout for the window and CardLayout for the screens.
     */
    public AcademicProjectApplet() {
        super("Academic Project Evaluation System");

        // Create the data objects
        loginObj = new Login();
        studentObj = new Student();
        evaluationObj = new Evaluation();
        resultObj = new Result();

        decimalFormat = new DecimalFormat("0.00");

        // Window size
        setSize(1425, 1170);
        setResizable(true);
        setLocationRelativeTo(null);

        // Close the window when the user clicks the X button
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // CardLayout lets us show one screen at a time
        cardLayout = new CardLayout();
        mainContainer = new Panel();
        mainContainer.setLayout(cardLayout);
        mainContainer.setBackground(new Color(245, 245, 245));

        // Build all four screens
        createLoginPanel();
        createStudentDetailsPanel();
        createEvaluationPanel();
        createResultPanel();

        // Add every screen to the container with a name
        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(studentDetailsPanel, "STUDENT_DETAILS");
        mainContainer.add(evaluationPanel, "EVALUATION");
        mainContainer.add(resultPanel, "RESULT");

        // BorderLayout: mainContainer fills the whole window
        setLayout(new java.awt.BorderLayout());
        add(mainContainer, java.awt.BorderLayout.CENTER);

        // Show the login screen first
        cardLayout.show(mainContainer, "LOGIN");

        setVisible(true);
    }

    /*
     * Builds the Login screen.
     * Username: admin   Password: 1234
     */
    private void createLoginPanel() {
        loginPanel = new Panel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(250, 250, 250));

        lblLoginTitle = new Label("Academic Project Evaluation System");
        lblLoginTitle.setFont(new Font("Arial", Font.BOLD, 42));
        lblLoginTitle.setAlignment(Label.CENTER);
        lblLoginTitle.setBounds((getWidth() > 0 ? (getWidth() - 1230) / 2 : 60), 150, 1230, 67);
        loginPanel.add(lblLoginTitle);

        lblUsername = new Label("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 24));
        lblUsername.setBounds(375, 315, 180, 52);
        loginPanel.add(lblUsername);

        txtUsername = new TextField(15);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 24));
        txtUsername.setBounds(570, 315, 390, 52);
        loginPanel.add(txtUsername);

        lblPassword = new Label("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        lblPassword.setBounds(375, 412, 180, 52);
        loginPanel.add(lblPassword);

        txtPassword = new TextField(15);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        // This makes the typed characters appear as stars
        txtPassword.setEchoChar('*');
        txtPassword.setBounds(570, 412, 390, 52);
        loginPanel.add(txtPassword);

        lblLoginError = new Label("");
        lblLoginError.setFont(new Font("Arial", Font.PLAIN, 21));
        lblLoginError.setForeground(Color.RED);
        lblLoginError.setAlignment(Label.CENTER);
        lblLoginError.setBounds(60, 495, 1230, 52);
        loginPanel.add(lblLoginError);

        btnLogin = new Button("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 24));
        btnLogin.setBounds(592, 585, 240, 63);
        btnLogin.addActionListener(this);
        loginPanel.add(btnLogin);
    }

    /*
     * Builds the Student Details screen.
     */
    private void createStudentDetailsPanel() {
        studentDetailsPanel = new Panel();
        studentDetailsPanel.setLayout(null);
        studentDetailsPanel.setBackground(new Color(250, 250, 250));

        Label lblStudentTitle = new Label("Student Details");
        lblStudentTitle.setFont(new Font("Arial", Font.BOLD, 39));
        lblStudentTitle.setAlignment(Label.CENTER);
        lblStudentTitle.setBounds(150, 82, 900, 63);
        studentDetailsPanel.add(lblStudentTitle);

        Label lblStudentName = new Label("Student Name:");
        lblStudentName.setFont(new Font("Arial", Font.PLAIN, 24));
        lblStudentName.setBounds(180, 217, 225, 52);
        studentDetailsPanel.add(lblStudentName);

        txtStudentName = new TextField(15);
        txtStudentName.setFont(new Font("Arial", Font.PLAIN, 24));
        txtStudentName.setBounds(435, 217, 420, 52);
        studentDetailsPanel.add(txtStudentName);

        Label lblRollNumber = new Label("Roll Number:");
        lblRollNumber.setFont(new Font("Arial", Font.PLAIN, 24));
        lblRollNumber.setBounds(180, 322, 225, 52);
        studentDetailsPanel.add(lblRollNumber);

        txtRollNumber = new TextField(15);
        txtRollNumber.setFont(new Font("Arial", Font.PLAIN, 24));
        txtRollNumber.setBounds(435, 322, 420, 52);
        studentDetailsPanel.add(txtRollNumber);

        Label lblProjectName = new Label("Project Name:");
        lblProjectName.setFont(new Font("Arial", Font.PLAIN, 24));
        lblProjectName.setBounds(180, 427, 225, 52);
        studentDetailsPanel.add(lblProjectName);

        txtProjectName = new TextField(15);
        txtProjectName.setFont(new Font("Arial", Font.PLAIN, 24));
        txtProjectName.setBounds(435, 427, 420, 52);
        studentDetailsPanel.add(txtProjectName);

        Label lblGuideName = new Label("Guide Name:");
        lblGuideName.setFont(new Font("Arial", Font.PLAIN, 24));
        lblGuideName.setBounds(180, 532, 225, 52);
        studentDetailsPanel.add(lblGuideName);

        txtGuideName = new TextField(15);
        txtGuideName.setFont(new Font("Arial", Font.PLAIN, 24));
        txtGuideName.setBounds(435, 532, 420, 52);
        studentDetailsPanel.add(txtGuideName);

        btnNext = new Button("Next");
        btnNext.setFont(new Font("Arial", Font.BOLD, 24));
        btnNext.setBounds(480, 660, 240, 63);
        btnNext.addActionListener(this);
        studentDetailsPanel.add(btnNext);
    }

    /*
     * Builds the Project Evaluation screen with all the marks fields.
     */
    private void createEvaluationPanel() {
        evaluationPanel = new Panel();
        evaluationPanel.setLayout(null);
        evaluationPanel.setBackground(new Color(250, 250, 250));

        Label lblEvalTitle = new Label("Project Evaluation");
        lblEvalTitle.setFont(new Font("Arial", Font.BOLD, 39));
        lblEvalTitle.setAlignment(Label.CENTER);
        lblEvalTitle.setBounds(150, 37, 900, 57);
        evaluationPanel.add(lblEvalTitle);

        lblProjectIdea = new Label("Project Idea:");
        lblProjectIdea.setFont(new Font("Arial", Font.PLAIN, 24));
        lblProjectIdea.setBounds(180, 135, 225, 45);
        evaluationPanel.add(lblProjectIdea);

        txtProjectIdea = new TextField(5);
        txtProjectIdea.setFont(new Font("Arial", Font.PLAIN, 24));
        txtProjectIdea.setBounds(450, 135, 135, 45);
        evaluationPanel.add(txtProjectIdea);

        Label lblMaxIdea = new Label("/ 10");
        lblMaxIdea.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxIdea.setBounds(600, 135, 90, 45);
        evaluationPanel.add(lblMaxIdea);

        Label lblImplementation = new Label("Implementation:");
        lblImplementation.setFont(new Font("Arial", Font.PLAIN, 24));
        lblImplementation.setBounds(180, 202, 255, 45);
        evaluationPanel.add(lblImplementation);

        txtImplementation = new TextField(5);
        txtImplementation.setFont(new Font("Arial", Font.PLAIN, 24));
        txtImplementation.setBounds(450, 202, 135, 45);
        evaluationPanel.add(txtImplementation);

        Label lblMaxImpl = new Label("/ 20");
        lblMaxImpl.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxImpl.setBounds(600, 202, 90, 45);
        evaluationPanel.add(lblMaxImpl);

        Label lblDocumentation = new Label("Documentation:");
        lblDocumentation.setFont(new Font("Arial", Font.PLAIN, 24));
        lblDocumentation.setBounds(180, 270, 255, 45);
        evaluationPanel.add(lblDocumentation);

        txtDocumentation = new TextField(5);
        txtDocumentation.setFont(new Font("Arial", Font.PLAIN, 24));
        txtDocumentation.setBounds(450, 270, 135, 45);
        evaluationPanel.add(txtDocumentation);

        Label lblMaxDoc = new Label("/ 20");
        lblMaxDoc.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxDoc.setBounds(600, 270, 90, 45);
        evaluationPanel.add(lblMaxDoc);

        Label lblPresentation = new Label("Presentation:");
        lblPresentation.setFont(new Font("Arial", Font.PLAIN, 24));
        lblPresentation.setBounds(180, 337, 255, 45);
        evaluationPanel.add(lblPresentation);

        txtPresentation = new TextField(5);
        txtPresentation.setFont(new Font("Arial", Font.PLAIN, 24));
        txtPresentation.setBounds(450, 337, 135, 45);
        evaluationPanel.add(txtPresentation);

        Label lblMaxPres = new Label("/ 20");
        lblMaxPres.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxPres.setBounds(600, 337, 90, 45);
        evaluationPanel.add(lblMaxPres);

        Label lblViva = new Label("Viva:");
        lblViva.setFont(new Font("Arial", Font.PLAIN, 24));
        lblViva.setBounds(180, 405, 255, 45);
        evaluationPanel.add(lblViva);

        txtViva = new TextField(5);
        txtViva.setFont(new Font("Arial", Font.PLAIN, 24));
        txtViva.setBounds(450, 405, 135, 45);
        evaluationPanel.add(txtViva);

        Label lblMaxViva = new Label("/ 20");
        lblMaxViva.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxViva.setBounds(600, 405, 90, 45);
        evaluationPanel.add(lblMaxViva);

        Label lblInnovation = new Label("Innovation:");
        lblInnovation.setFont(new Font("Arial", Font.PLAIN, 24));
        lblInnovation.setBounds(180, 472, 255, 45);
        evaluationPanel.add(lblInnovation);

        txtInnovation = new TextField(5);
        txtInnovation.setFont(new Font("Arial", Font.PLAIN, 24));
        txtInnovation.setBounds(450, 472, 135, 45);
        evaluationPanel.add(txtInnovation);

        Label lblMaxInno = new Label("/ 10");
        lblMaxInno.setFont(new Font("Arial", Font.PLAIN, 24));
        lblMaxInno.setBounds(600, 472, 90, 45);
        evaluationPanel.add(lblMaxInno);

        Label lblFeedback = new Label("Feedback:");
        lblFeedback.setFont(new Font("Arial", Font.PLAIN, 24));
        lblFeedback.setBounds(180, 547, 180, 45);
        evaluationPanel.add(lblFeedback);

        txtFeedback = new TextArea(4, 28);
        txtFeedback.setFont(new Font("Arial", Font.PLAIN, 21));
        txtFeedback.setBounds(180, 600, 810, 135);
        evaluationPanel.add(txtFeedback);

        lblEvalError = new Label("");
        lblEvalError.setFont(new Font("Arial", Font.PLAIN, 21));
        lblEvalError.setForeground(Color.RED);
        lblEvalError.setAlignment(Label.CENTER);
        lblEvalError.setBounds(60, 750, 1230, 42);
        evaluationPanel.add(lblEvalError);

        btnEvaluate = new Button("Evaluate");
        btnEvaluate.setFont(new Font("Arial", Font.BOLD, 24));
        btnEvaluate.setBounds(480, 817, 240, 63);
        btnEvaluate.addActionListener(this);
        evaluationPanel.add(btnEvaluate);
    }

    /*
     * Builds the Result screen.
     */
    private void createResultPanel() {
        resultPanel = new Panel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(new Color(250, 250, 250));

        Label lblLine1 = new Label("------------------------------------------------------------");
        lblLine1.setAlignment(Label.CENTER);
        lblLine1.setBounds(60, 33, 1230, 33);
        resultPanel.add(lblLine1);

        Label lblTitle = new Label("PROJECT RESULT");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 42));
        lblTitle.setAlignment(Label.CENTER);
        lblTitle.setBounds(60, 69, 1230, 57);
        resultPanel.add(lblTitle);

        Label lblLine2 = new Label("------------------------------------------------------------");
        lblLine2.setAlignment(Label.CENTER);
        lblLine2.setBounds(60, 129, 1230, 33);
        resultPanel.add(lblLine2);

        lblResultName = new Label("Student Name : ");
        lblResultName.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultName.setBounds(225, 180, 900, 42);
        resultPanel.add(lblResultName);

        lblResultRoll = new Label("Roll Number  : ");
        lblResultRoll.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultRoll.setBounds(225, 237, 900, 42);
        resultPanel.add(lblResultRoll);

        lblResultProject = new Label("Project Name : ");
        lblResultProject.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultProject.setBounds(225, 294, 900, 42);
        resultPanel.add(lblResultProject);

        lblResultTotal = new Label("Total Marks  : ");
        lblResultTotal.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultTotal.setBounds(225, 366, 900, 42);
        resultPanel.add(lblResultTotal);

        lblResultPercentage = new Label("Percentage   : ");
        lblResultPercentage.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultPercentage.setBounds(225, 423, 900, 42);
        resultPanel.add(lblResultPercentage);

        lblResultGrade = new Label("Grade        : ");
        lblResultGrade.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultGrade.setBounds(225, 480, 900, 42);
        resultPanel.add(lblResultGrade);

        lblResultStatus = new Label("Status       : ");
        lblResultStatus.setFont(new Font("Arial", Font.BOLD, 27));
        lblResultStatus.setBounds(225, 537, 900, 42);
        resultPanel.add(lblResultStatus);

        Label lblResultFeedback = new Label("Feedback:");
        lblResultFeedback.setFont(new Font("Arial", Font.PLAIN, 24));
        lblResultFeedback.setBounds(225, 600, 900, 42);
        resultPanel.add(lblResultFeedback);

        // TextArea is used so that long feedback wraps to a new line
        txtFeedbackResult = new TextArea(3, 28);
        txtFeedbackResult.setFont(new Font("Arial", Font.PLAIN, 21));
        txtFeedbackResult.setEditable(false);
        txtFeedbackResult.setBounds(225, 648, 900, 120);
        resultPanel.add(txtFeedbackResult);

        lblResultMessage = new Label("");
        lblResultMessage.setFont(new Font("Arial", Font.PLAIN, 21));
        lblResultMessage.setAlignment(Label.CENTER);
        lblResultMessage.setBounds(60, 780, 1230, 42);
        resultPanel.add(lblResultMessage);

        Label lblLine3 = new Label("------------------------------------------------------------");
        lblLine3.setAlignment(Label.CENTER);
        lblLine3.setBounds(60, 828, 1230, 33);
        resultPanel.add(lblLine3);

        btnSaveCSV = new Button("Save to CSV");
        btnSaveCSV.setFont(new Font("Arial", Font.BOLD, 24));
        btnSaveCSV.setBounds(330, 882, 300, 63);
        btnSaveCSV.addActionListener(this);
        resultPanel.add(btnSaveCSV);

        btnNewEvaluation = new Button("New Evaluation");
        btnNewEvaluation.setFont(new Font("Arial", Font.BOLD, 24));
        btnNewEvaluation.setBounds(690, 882, 300, 63);
        btnNewEvaluation.addActionListener(this);
        resultPanel.add(btnNewEvaluation);
    }

    /*
     * This method runs whenever any button is clicked.
     * We simply compare the clicked button with our button variables.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Login button clicked
        if (e.getSource() == btnLogin) {
            handleLogin();
        }
        // Next button clicked
        else if (e.getSource() == btnNext) {
            handleStudentDetails();
        }
        // Evaluate button clicked
        else if (e.getSource() == btnEvaluate) {
            handleEvaluation();
        }
        // Save CSV button clicked
        else if (e.getSource() == btnSaveCSV) {
            saveToCSV();
        }
        // New Evaluation button clicked
        else if (e.getSource() == btnNewEvaluation) {
            handleNewEvaluation();
        }
    }

    /*
     * Login handling:
     * - Read username and password
     * - Check using the Login object
     * - If correct go to Student Details, else show the error message
     */
    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        loginObj.setUsername(username);
        loginObj.setPassword(password);

        if (loginObj.isValidLogin()) {
            // Login successful
            lblLoginError.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
            cardLayout.show(mainContainer, "STUDENT_DETAILS");
        } else {
            // Login failed
            lblLoginError.setText("Invalid Username or Password");
            txtPassword.setText("");
        }
    }

    /*
     * Student details handling:
     * - Read the four text fields
     * - Store them inside the Student object
     * - Move to the evaluation screen
     */
    private void handleStudentDetails() {
        studentObj.setStudentName(txtStudentName.getText().trim());
        studentObj.setRollNumber(txtRollNumber.getText().trim());
        studentObj.setProjectName(txtProjectName.getText().trim());
        studentObj.setGuideName(txtGuideName.getText().trim());

        cardLayout.show(mainContainer, "EVALUATION");
    }

    /*
     * Evaluation handling:
     * 1. Read all marks
     * 2. Validate the marks
     * 3. Calculate total, percentage, grade and status
     * 4. Show the result screen
     */
    private void handleEvaluation() {
        try {
            // Empty fields are treated as 0
            double idea = readMark(txtProjectIdea);
            double implementation = readMark(txtImplementation);
            double documentation = readMark(txtDocumentation);
            double presentation = readMark(txtPresentation);
            double viva = readMark(txtViva);
            double innovation = readMark(txtInnovation);

            // Store marks and feedback in the Evaluation object
            evaluationObj.setProjectIdea(idea);
            evaluationObj.setImplementation(implementation);
            evaluationObj.setDocumentation(documentation);
            evaluationObj.setPresentation(presentation);
            evaluationObj.setViva(viva);
            evaluationObj.setInnovation(innovation);
            evaluationObj.setFeedback(txtFeedback.getText().trim());

            // Step 2: validate the marks
            if (!evaluationObj.validateMarks()) {
                lblEvalError.setText("Error: Marks must be within the allowed limit!");
                return;
            }

            // Step 3: calculate total, percentage, grade and status
            evaluationObj.calculateResults();

            // Step 4: fill the result screen and show it
            displayResult();
            lblEvalError.setText("");
            cardLayout.show(mainContainer, "RESULT");

        } catch (NumberFormatException ex) {
            // This happens when the user types letters instead of numbers
            lblEvalError.setText("Error: Please enter valid numeric marks!");
        }
    }

    /*
     * Small helper method: reads one mark from a text field.
     * Returns 0 if the field is empty.
     * If the text is not a number, Double.parseDouble throws NumberFormatException.
     */
    private double readMark(TextField field) {
        String text = field.getText().trim();
        if (text.length() == 0) {
            return 0;
        }
        return Double.parseDouble(text);
    }

    /*
     * Fills the result screen with the calculated values.
     */
    private void displayResult() {
        // Copy the student and evaluation objects into the result object
        resultObj.setStudent(studentObj);
        resultObj.setEvaluation(evaluationObj);

        lblResultName.setText("Student Name : " + resultObj.getStudent().getStudentName());
        lblResultRoll.setText("Roll Number  : " + resultObj.getStudent().getRollNumber());
        lblResultProject.setText("Project Name : " + resultObj.getStudent().getProjectName());

        // Total marks rounded to the nearest whole number
        long total = Math.round(evaluationObj.getTotalMarks());
        lblResultTotal.setText("Total Marks  : " + total + " / " + Evaluation.TOTAL_MAX_MARKS);

        lblResultPercentage.setText("Percentage   : "
                + decimalFormat.format(evaluationObj.getPercentage()) + "%");
        lblResultGrade.setText("Grade        : " + evaluationObj.getGrade());
        lblResultStatus.setText("Status       : " + evaluationObj.getStatus());

        // Green colour for PASS and red colour for FAIL
        if (evaluationObj.getStatus().equals("PASS")) {
            lblResultStatus.setForeground(new Color(0, 128, 0));
        } else {
            lblResultStatus.setForeground(Color.RED);
        }

        // Show the feedback text
        String feedback = evaluationObj.getFeedback();
        if (feedback == null || feedback.length() == 0) {
            txtFeedbackResult.setText("No feedback provided.");
        } else {
            txtFeedbackResult.setText(feedback);
        }
    }

    /*
     * Save student evaluation data to CSV file
     */
    private void saveToCSV() {
        try {
            String fileName = "student_evaluations.csv";
            boolean fileExists = new java.io.File(fileName).exists();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            if (!fileExists) {
                writer.write("Student_Name,Roll_Number,Project_Name,Guide_Name,Project_Idea,Implementation,Documentation,Presentation,Viva,Innovation,Total_Marks,Percentage,Grade,Status,Feedback");
                writer.newLine();
            }

            String feedback = evaluationObj.getFeedback();
            if (feedback == null) {
                feedback = "";
            }
            feedback = feedback.replace("\"", "\"\"");
            if (feedback.contains(",") || feedback.contains("\n") || feedback.contains("\"")) {
                feedback = "\"" + feedback + "\"";
            }

            String line = String.join(",",
                    csvEscape(studentObj.getStudentName()),
                    csvEscape(studentObj.getRollNumber()),
                    csvEscape(studentObj.getProjectName()),
                    csvEscape(studentObj.getGuideName()),
                    String.valueOf(evaluationObj.getProjectIdea()),
                    String.valueOf(evaluationObj.getImplementation()),
                    String.valueOf(evaluationObj.getDocumentation()),
                    String.valueOf(evaluationObj.getPresentation()),
                    String.valueOf(evaluationObj.getViva()),
                    String.valueOf(evaluationObj.getInnovation()),
                    String.valueOf(Math.round(evaluationObj.getTotalMarks())),
                    decimalFormat.format(evaluationObj.getPercentage()),
                    evaluationObj.getGrade(),
                    evaluationObj.getStatus(),
                    feedback);

            writer.write(line);
            writer.newLine();
            writer.close();

            // Show success message
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (lblResultMessage != null) {
                lblResultMessage.setText("Data saved to " + fileName);
                lblResultMessage.setForeground(new Color(0, 128, 0));
            }
            lblEvalError.setText("Data saved to " + fileName);
            lblEvalError.setForeground(new Color(0, 128, 0));
        } catch (IOException ex) {
            if (lblResultMessage != null) {
                lblResultMessage.setText("Error saving to CSV: " + ex.getMessage());
                lblResultMessage.setForeground(Color.RED);
            }
            lblEvalError.setText("Error saving to CSV: " + ex.getMessage());
            lblEvalError.setForeground(Color.RED);
        }
    }

    private String csvEscape(String value) {
        if (value == null) {
            return "";
        }
        String escaped = value.replace("\"", "\"\"");
        if (escaped.contains(",") || escaped.contains("\n") || escaped.contains("\"") || escaped.contains("\r")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }

    /*
     * New Evaluation button handling:
     * - Clear all objects
     * - Clear all text fields
     * - Go back to the student details screen
     */
    private void handleNewEvaluation() {
        studentObj.reset();
        evaluationObj.reset();
        resultObj.reset();

        txtStudentName.setText("");
        txtRollNumber.setText("");
        txtProjectName.setText("");
        txtGuideName.setText("");

        txtProjectIdea.setText("");
        txtImplementation.setText("");
        txtDocumentation.setText("");
        txtPresentation.setText("");
        txtViva.setText("");
        txtInnovation.setText("");
        txtFeedback.setText("");
        lblEvalError.setText("");

        lblResultStatus.setForeground(Color.BLACK);

        if (lblResultMessage != null) {
            lblResultMessage.setText("");
        }
        cardLayout.show(mainContainer, "STUDENT_DETAILS");
    }

    /*
     * main method: this is the starting point of the program.
     * It simply creates the object, which opens the window.
     */
    public static void main(String[] args) {
        new AcademicProjectApplet();
    }
}