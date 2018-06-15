package servlets.LogControllers;

import beans.UserAccount;
import utils.MysqlUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logOut"})
public class logOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserAccount log = MysqlUtils.getLoginedUser(session);
        MysqlUtils.deleteUserCookie(resp);
        session.invalidate();

        // Redirect (Перенаправить) к странице login.
        if(log == null){
            resp.sendRedirect(req.getContextPath() + "/login");
        }else {
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
