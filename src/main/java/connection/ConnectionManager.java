package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/shopApp?serverTimezone=EST5EDT";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sylmar123";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not connect");
        } catch (SQLException throwables) {
            System.out.println("Something went wrong with ");
        }
    }

}
