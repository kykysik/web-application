package DAO;

import beans.UserAccount;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public interface DaoMsqlUt {
    void storeConnection(ServletRequest request);

    Connection getStoredConnection(ServletRequest request);

    void storeLoginedUser(HttpSession session, UserAccount loginedUser);

    UserAccount getLoginedUser(HttpSession session);

    void storeUserCookie(HttpServletResponse response, UserAccount user);

    String getUserNameInCookie(HttpServletRequest request);

    void deleteUserCookie(HttpServletResponse response);
}
