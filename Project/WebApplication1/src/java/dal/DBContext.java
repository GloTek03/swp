package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    public DBContext(){
          connection = DBContext();
    }
    protected Connection connection;
    public Connection DBContext() {
        final String url = "jdbc:mysql://localhost:3306/swp_v2";
        final String user = "root";
        final String pass = "30102003";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e);
        }
        return connection;

    }

}
