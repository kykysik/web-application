package connections;

import DAO.DaoConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConUt implements DaoConn {

    public  Connection getConnection()
            throws ClassNotFoundException, SQLException {

        String userName = "root";
        String password = "1";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionUrl, userName, password);

        return connection;
    }

    public  void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public  void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
