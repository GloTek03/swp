/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dal;

import java.io.Serializable;
import java.net.Authenticator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

/**
 *
 * @author acer
 */
public class UserDAO extends DBContext implements Serializable {

    public User getAccountDetails(String username)
            throws SQLException, ClassNotFoundException {
        User account = new User();
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            //2. Create SQL String
            String sql = "Select *"
                    + "FROM account "
                    + "WHERE username = ? ";
            //3. Create Statement
            stm = DBContext().prepareStatement(sql);
            stm.setString(1, username);
            //4. Excute Query
            rs = stm.executeQuery();
            //5. Process Result
            if (rs.next()) {
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setStatus(rs.getBoolean("status"));
                account.setFullname(rs.getString("full_name"));
                account.setDob(rs.getDate("dob"));
                account.setGender(rs.getString("gender"));
                account.setEmail(rs.getString("email"));
                account.setMobile(rs.getString("mobile"));
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return account;
    }

    public boolean updateAccountInfo(String fullname, Date dob, String gender, String email, String mobile, String username)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update account "
                        + "SET full_name = ?"
                        + ", dob = ? "
                        + ", gender = ?"
                        + ", email=?"
                        + ", mobile =?"
                        + "WHERE username = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, fullname);
                stm.setDate(2, dob);
                stm.setString(3, gender);
                stm.setString(4, email);
                stm.setString(5, mobile);
                stm.setString(6, username);
                //4. Excute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccountPassword(String password, String username)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update account "
                        + "SET password = ?"
                        + "WHERE username = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);
                //4. Excute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkUsernameExist(String username)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select *"
                        + "FROM account "
                        + "WHERE username = ? ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

        public boolean checkEmailExist(String email)
            throws SQLException, ClassNotFoundException {
        DBContext connect = new DBContext();
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = connect.DBContext();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select *"
                        + "FROM account "
                        + "WHERE email = ? ";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public void EmailOTPSender(String receiverEmail, char[] otp) {
        String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPort = 2525;
        String smtpUsername = "ee99f64bb248d9";
        String smtpPassword = "8cbf2ae11f0afb";

        // Sender email address
        String senderEmail = "trinhdung1103@gmail.com";


        // Create properties for the JavaMail session
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

            // Set the email subject and content
            message.setSubject("OTP Confirmation");
            String messageText = "Your OTP to reset password is: " + String.valueOf(otp) + "\n*Please note that the OTP will be expired in 10 minutes";
            message.setText(messageText);
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void EmailChangePassSender(String receiverEmail) {
        String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPort = 2525;
        String smtpUsername = "ee99f64bb248d9";
        String smtpPassword = "8cbf2ae11f0afb";

        // Sender email address
        String senderEmail = "trinhdung1103@gmail.com";

        // Create properties for the JavaMail session
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

            // Set the email subject and content
            message.setSubject("Change password");
            String messageText = "Your password of LMS account has been changed!";
            message.setText(messageText);
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static char[] generateOTP(int len) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[len];
        for (int i = 0; i < len; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPort = 587; // Mailtrap SMTP port
        String smtpUsername = "73a689b8bb4f0f";
        String smtpPassword = "ac82e89daf91e0";

        // Sender and recipient email addresses
        String senderEmail = "bang-email@example.com";
        String recipientEmail = "bangthuhai-email@example.com";

        // Create properties for the JavaMail session
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a Session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content
            message.setSubject("Hello from Mailtrap");
            message.setText("This is a test email sent via Mailtrap.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
