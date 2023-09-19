
package Model;

public class Account {
    private String fullname;
    private String dateEnrol;
    private String role;
    private boolean status;

    public Account() {
    }

    public Account(String fullname, String dateEnrol, String role, boolean status) {
        this.fullname = fullname;
        this.dateEnrol = dateEnrol;
        this.role = role;
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateEnrol() {
        return dateEnrol;
    }

    public void setDateEnrol(String dateEnrol) {
        this.dateEnrol = dateEnrol;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
