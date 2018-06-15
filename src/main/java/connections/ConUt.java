package connections;

import javax.servlet.http.Cookie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUt {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        String userName = "root";
        String password = "1";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);

        return connection;
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
