package servlets.LogControllers;

import beans.UserAccount;
import utils.DBUtils;
import utils.MysqlUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet(urlPatterns = { "/registration" })

public class Registration extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward (перенаправить) к странице /WEB-INF/views/loginView.jsp
        // (Пользователь не может прямо получить доступ
        // к страницам JSP расположенные в папке WEB-INF).
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");

        dispatcher.forward(request, response);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MysqlUtils.getStoredConnection(request);

        String userName = request.getParameter("userName");
        String gender =  request.getParameter("gender");
        String password =  request.getParameter("password");

        UserAccount account = new UserAccount(userName,gender,password);
        String errorString = null;

        // Кодом продукта является строка [a-zA-Z_0-9]
        // Имеет минимум 1 символ.
        String regex = "\\w+";

        // Сохранить информацию в request attribute перед тем как forward к views.
        if (userName == null || userName.length() == 0 || password == null || !password.matches(regex) || password.length() == 0) {
            errorString = "Name or pass";
        }
        else {

            try {
               DBUtils.registr(conn, account);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
            // Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("account", account);

          if(errorString != null){

            RequestDispatcher dispatcher
                    = request.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect (Перенаправить) на страницу /login
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
