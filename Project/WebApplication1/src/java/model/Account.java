/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class Account {
    private int ID;
    private String user;
    private String pass;
    private String description;
    private int activate;
    private String roleName;
    private String fullName;
    private Date dob;
    private String gender;
    private String email;
    private String mobile;
    private String dateEnroll;
    private Gender g;
    private int role;
    private Accountinclass accountinclass;
    private Accountnotclass accountnotclass;

    public Account(String user, String pass, int activate) {
        this.user = user;
        this.pass = pass;
        this.activate = activate;
    }
    
    public Account(String username, String passowrd, String role, int status, String fullname, Date dob, String gender, String email, String mobile) {
        this.user = username;
        this.pass = passowrd;
        this.roleName = role;
        this.activate = status;
        this.fullName = fullname;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
    }

    public Account(int ID, String user, String pass, int activate, String roleName, String fullName, Date dob, String gender, String email, String mobile, String dateEnroll, Gender g, int role, Accountinclass accountinclass, Accountnotclass accountnotclass) {
        this.ID = ID;
        this.user = user;
        this.pass = pass;
        this.activate = activate;
        this.roleName = roleName;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.dateEnroll = dateEnroll;
        this.g = g;
        this.role = role;
        this.accountinclass = accountinclass;
        this.accountnotclass = accountnotclass;
    }

    public Account(String user, String pass, int activate, String roleName) {
        this.user = user;
        this.pass = pass;
        this.activate = activate;
        this.roleName = roleName;
    }

    public Account(String user, String pass, int activate, String roleName, String fullName, Date dob, String gender, String email, String mobile) {
        this.user = user;
        this.pass = pass;
        this.activate = activate;
        this.roleName = roleName;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
    }
    
    public Account(String username, String password, Date dob, Gender gender, String email, String mobile, String fullname, String dateEnrol, String role, int status) {
        this.user = username;
        this.pass = password;
        this.dob = dob;
        this.g = gender;
        this.email = email;
        this.mobile = mobile;
        this.fullName = fullname;
        this.dateEnroll = dateEnrol;
        this.roleName = role;
        this.activate = status;
    }

    public Account(String user, int activate, String roleName, String fullName, String dateEnroll) {
        this.user = user;
        this.activate = activate;
        this.roleName = roleName;
        this.fullName = fullName;
        this.dateEnroll = dateEnroll;
    }

    public Account() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Accountinclass getAccountinclass() {
        return accountinclass;
    }

    public void setAccountinclass(Accountinclass accountinclass) {
        this.accountinclass = accountinclass;
    }

    public Accountnotclass getAccountnotclass() {
        return accountnotclass;
    }

    public void setAccountnotclass(Accountnotclass accountnotclass) {
        this.accountnotclass = accountnotclass;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    

    public String getDateEnroll() {
        return dateEnroll;
    }

    public void setDateEnroll(String dateEnroll) {
        this.dateEnroll = dateEnroll;
    }

    public Gender getG() {
        return g;
    }

    public void setG(Gender g) {
        this.g = g;
    }
    
    

    public enum Gender{
        Male,
        Female,
        Other
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Account{" + "ID=" + ID + ", user=" + user + ", pass=" + pass + ", activate=" + activate + ", roleName=" + roleName + ", fullName=" + fullName + ", dob=" + dob + ", gender=" + gender + ", email=" + email + ", mobile=" + mobile + ", dateEnroll=" + dateEnroll + ", g=" + g + ", role=" + role + ", accountinclass=" + accountinclass + ", accountnotclass=" + accountnotclass + '}';
    }


    
}
