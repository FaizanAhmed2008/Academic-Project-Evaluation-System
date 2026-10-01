import java.io.Serializable;

/*
 * Login.java
 * This class represents login credentials for authentication.
 * It is a simple model class used to store and verify username and password.
 */
public class Login implements Serializable {
    
    // Hardcoded valid credentials as per project requirements
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";
    
    private String username;
    private String password;
    
    // Default constructor
    public Login() {
        this.username = "";
        this.password = "";
    }
    
    // Parameterized constructor
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getter and Setter methods for username
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Getter and Setter methods for password
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /*
     * Method to validate login credentials
     * Returns true if username and password match the hardcoded values, else false
     */
    public boolean isValidLogin() {
        // Check if credentials are correct
        if (username != null && password != null) {
            if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                return true;
            }
        }
        return false;
    }
    
    /*
     * Method to reset login fields
     */
    public void reset() {
        this.username = "";
        this.password = "";
    }
}