package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoConn {
   Connection getConnection() throws ClassNotFoundException, SQLException ;

   void closeQuietly(Connection conn);

   void rollbackQuietly(Connection conn);
}
