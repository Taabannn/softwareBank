package ir.maktab58.softwareBank.models;

/**
 * @author Taban Soleymani
 */
public class Admin {
    private String username = "admin";
    private String password = "admin";

    public boolean isUserAdmin(String username, String password) {
        if (username.equals(this.username) && password.equals(this.password))
            return true;
        return false;
    }
}
