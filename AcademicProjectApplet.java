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
    private TextArea txtFeedbackResult;
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
        setSize(440, 480);
        setResizable(false);

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
        mainContainer.setBackground(Color.LIGHT_GRAY);

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
        loginPanel.setBackground(Color.WHITE);

        lblLoginTitle = new Label("Academic Project Evaluation System");
        lblLoginTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblLoginTitle.setAlignment(Label.CENTER);
        lblLoginTitle.setBounds(20, 40, 400, 30);
        loginPanel.add(lblLoginTitle);

        lblUsername = new Label("Username:");
        lblUsername.setBounds(60, 120, 80, 25);
        loginPanel.add(lblUsername);

        txtUsername = new TextField(15);
        txtUsername.setBounds(150, 120, 180, 25);
        loginPanel.add(txtUsername);

        lblPassword = new Label("Password:");
        lblPassword.setBounds(60, 170, 80, 25);
        loginPanel.add(lblPassword);

        txtPassword = new TextField(15);
        // This makes the typed characters appear as stars
        txtPassword.setEchoChar('*');
        txtPassword.setBounds(150, 170, 180, 25);
        loginPanel.add(txtPassword);

        lblLoginError = new Label("");
        lblLoginError.setForeground(Color.RED);
        lblLoginError.setAlignment(Label.CENTER);
        lblLoginError.setBounds(20, 210, 400, 25);
        loginPanel.add(lblLoginError);

        btnLogin = new Button("Login");
        btnLogin.setBounds(160, 250, 100, 30);
        btnLogin.addActionListener(this);
        loginPanel.add(btnLogin);
    }

    /*
     * Builds the Student Details screen.
     */
    private void createStudentDetailsPanel() {
        studentDetailsPanel = new Panel();
        studentDetailsPanel.setLayout(null);
        studentDetailsPanel.setBackground(Color.WHITE);

        Label lblStudentTitle = new Label("Student Details");
        lblStudentTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblStudentTitle.setAlignment(Label.CENTER);
        lblStudentTitle.setBounds(50, 40, 340, 30);
        studentDetailsPanel.add(lblStudentTitle);

        Label lblStudentName = new Label("Student Name:");
        lblStudentName.setBounds(50, 100, 100, 25);
        studentDetailsPanel.add(lblStudentName);

        txtStudentName = new TextField(15);
        txtStudentName.setBounds(160, 100, 180, 25);
        studentDetailsPanel.add(txtStudentName);

        Label lblRollNumber = new Label("Roll Number:");
        lblRollNumber.setBounds(50, 150, 100, 25);
        studentDetailsPanel.add(lblRollNumber);

        txtRollNumber = new TextField(15);
        txtRollNumber.setBounds(160, 150, 180, 25);
        studentDetailsPanel.add(txtRollNumber);

        Label lblProjectName = new Label("Project Name:");
        lblProjectName.setBounds(50, 200, 100, 25);
        studentDetailsPanel.add(lblProjectName);

        txtProjectName = new TextField(15);
        txtProjectName.setBounds(160, 200, 180, 25);
        studentDetailsPanel.add(txtProjectName);

        Label lblGuideName = new Label("Guide Name:");
        lblGuideName.setBounds(50, 250, 100, 25);
        studentDetailsPanel.add(lblGuideName);

        txtGuideName = new TextField(15);
        txtGuideName.setBounds(160, 250, 180, 25);
        studentDetailsPanel.add(txtGuideName);

        btnNext = new Button("Next");
        btnNext.setBounds(160, 300, 100, 30);
        btnNext.addActionListener(this);
        studentDetailsPanel.add(btnNext);
    }

    /*
     * Builds the Project Evaluation screen with all the marks fields.
     */
    private void createEvaluationPanel() {
        evaluationPanel = new Panel();
        evaluationPanel.setLayout(null);
        evaluationPanel.setBackground(Color.WHITE);

        Label lblEvalTitle = new Label("Project Evaluation");
        lblEvalTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblEvalTitle.setAlignment(Label.CENTER);
        lblEvalTitle.setBounds(50, 20, 340, 25);
        evaluationPanel.add(lblEvalTitle);

        lblProjectIdea = new Label("Project Idea:");
        lblProjectIdea.setBounds(50, 70, 100, 20);
        evaluationPanel.add(lblProjectIdea);

        txtProjectIdea = new TextField(5);
        txtProjectIdea.setBounds(170, 70, 60, 20);
        evaluationPanel.add(txtProjectIdea);

        Label lblMaxIdea = new Label("/ 10");
        lblMaxIdea.setBounds(235, 70, 50, 20);
        evaluationPanel.add(lblMaxIdea);

        Label lblImplementation = new Label("Implementation:");
        lblImplementation.setBounds(50, 100, 110, 20);
        evaluationPanel.add(lblImplementation);

        txtImplementation = new TextField(5);
        txtImplementation.setBounds(170, 100, 60, 20);
        evaluationPanel.add(txtImplementation);

        Label lblMaxImpl = new Label("/ 20");
        lblMaxImpl.setBounds(235, 100, 50, 20);
        evaluationPanel.add(lblMaxImpl);

        Label lblDocumentation = new Label("Documentation:");
        lblDocumentation.setBounds(50, 130, 110, 20);
        evaluationPanel.add(lblDocumentation);

        txtDocumentation = new TextField(5);
        txtDocumentation.setBounds(170, 130, 60, 20);
        evaluationPanel.add(txtDocumentation);

        Label lblMaxDoc = new Label("/ 20");
        lblMaxDoc.setBounds(235, 130, 50, 20);
        evaluationPanel.add(lblMaxDoc);

        Label lblPresentation = new Label("Presentation:");
        lblPresentation.setBounds(50, 160, 110, 20);
        evaluationPanel.add(lblPresentation);

        txtPresentation = new TextField(5);
        txtPresentation.setBounds(170, 160, 60, 20);
        evaluationPanel.add(txtPresentation);

        Label lblMaxPres = new Label("/ 20");
        lblMaxPres.setBounds(235, 160, 50, 20);
        evaluationPanel.add(lblMaxPres);

        Label lblViva = new Label("Viva:");
        lblViva.setBounds(50, 190, 110, 20);
        evaluationPanel.add(lblViva);

        txtViva = new TextField(5);
        txtViva.setBounds(170, 190, 60, 20);
        evaluationPanel.add(txtViva);

        Label lblMaxViva = new Label("/ 20");
        lblMaxViva.setBounds(235, 190, 50, 20);
        evaluationPanel.add(lblMaxViva);

        Label lblInnovation = new Label("Innovation:");
        lblInnovation.setBounds(50, 220, 110, 20);
        evaluationPanel.add(lblInnovation);

        txtInnovation = new TextField(5);
        txtInnovation.setBounds(170, 220, 60, 20);
        evaluationPanel.add(txtInnovation);

        Label lblMaxInno = new Label("/ 10");
        lblMaxInno.setBounds(235, 220, 50, 20);
        evaluationPanel.add(lblMaxInno);

        Label lblFeedback = new Label("Feedback:");
        lblFeedback.setBounds(50, 260, 80, 20);
        evaluationPanel.add(lblFeedback);

        txtFeedback = new TextArea(4, 28);
        txtFeedback.setBounds(50, 285, 300, 70);
        evaluationPanel.add(txtFeedback);

        lblEvalError = new Label("");
        lblEvalError.setForeground(Color.RED);
        lblEvalError.setAlignment(Label.CENTER);
        lblEvalError.setBounds(20, 365, 400, 20);
        evaluationPanel.add(lblEvalError);

        btnEvaluate = new Button("Evaluate");
        btnEvaluate.setBounds(160, 395, 100, 30);
        btnEvaluate.addActionListener(this);
        evaluationPanel.add(btnEvaluate);
    }

    /*
     * Builds the Result screen.
     */
    private void createResultPanel() {
        resultPanel = new Panel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.WHITE);

        Label lblLine1 = new Label("--------------------------------");
        lblLine1.setAlignment(Label.CENTER);
        lblLine1.setBounds(20, 15, 400, 15);
        resultPanel.add(lblLine1);

        Label lblTitle = new Label("PROJECT RESULT");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setAlignment(Label.CENTER);
        lblTitle.setBounds(20, 32, 400, 25);
        resultPanel.add(lblTitle);

        Label lblLine2 = new Label("--------------------------------");
        lblLine2.setAlignment(Label.CENTER);
        lblLine2.setBounds(20, 60, 400, 15);
        resultPanel.add(lblLine2);

        lblResultName = new Label("Student Name : ");
        lblResultName.setBounds(50, 85, 340, 20);
        resultPanel.add(lblResultName);

        lblResultRoll = new Label("Roll Number  : ");
        lblResultRoll.setBounds(50, 110, 340, 20);
        resultPanel.add(lblResultRoll);

        lblResultProject = new Label("Project Name : ");
        lblResultProject.setBounds(50, 135, 340, 20);
        resultPanel.add(lblResultProject);

        lblResultTotal = new Label("Total Marks  : ");
        lblResultTotal.setBounds(50, 170, 340, 20);
        resultPanel.add(lblResultTotal);

        lblResultPercentage = new Label("Percentage   : ");
        lblResultPercentage.setBounds(50, 195, 340, 20);
        resultPanel.add(lblResultPercentage);

        lblResultGrade = new Label("Grade        : ");
        lblResultGrade.setBounds(50, 220, 340, 20);
        resultPanel.add(lblResultGrade);

        lblResultStatus = new Label("Status       : ");
        lblResultStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lblResultStatus.setBounds(50, 245, 340, 20);
        resultPanel.add(lblResultStatus);

        Label lblResultFeedback = new Label("Feedback:");
        lblResultFeedback.setBounds(50, 275, 340, 20);
        resultPanel.add(lblResultFeedback);

        // TextArea is used so that long feedback wraps to a new line
        txtFeedbackResult = new TextArea(3, 28);
        txtFeedbackResult.setEditable(false);
        txtFeedbackResult.setBounds(50, 298, 300, 55);
        resultPanel.add(txtFeedbackResult);

        Label lblLine3 = new Label("--------------------------------");
        lblLine3.setAlignment(Label.CENTER);
        lblLine3.setBounds(20, 360, 400, 15);
        resultPanel.add(lblLine3);

        btnNewEvaluation = new Button("New Evaluation");
        btnNewEvaluation.setBounds(150, 385, 130, 30);
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