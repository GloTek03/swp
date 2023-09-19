package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    
    static Connection connection;

    public static Connection getConnection() {
        final String url = "jdbc:mysql://127.0.0.1:3306/swp1";
        final String user = "root";
        final String pass = "30102003";
//        final String url = "jdbc:mysql://sql12.freesqldatabase.com/sql12645551";
//        final String user = "sql12645551";
//        final String pass = "GzLHBITv2l";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
//here sonoo is database name, root is username and password   
            System.out.println();
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

}
