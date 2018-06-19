package servlets.PostControllers;

import beans.Post;
import beans.UserAccount;
import utils.DBUtils;
import utils.MysqlUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
@WebServlet("/insert")
*/
@WebServlet(urlPatterns = { "/insert" })

public class InsertController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/PostViews/InsertPostView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MysqlUtils mysqlUtils = new MysqlUtils();

        Connection conn = mysqlUtils.getStoredConnection(request);
        DBUtils db = new DBUtils();

        HttpSession session = request.getSession();

        UserAccount loginedUser = mysqlUtils.getLoginedUser(session);

        if (loginedUser == null) {
            request.setAttribute("msgError", "It's necessary to be logged in to load that page.");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        String title = request.getParameter("title");
        if (title == null || title.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String content = request.getParameter("content");
        if (content == null || content.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            db.insert(new Post(title, content, loginedUser.getUserName()));
        }catch (SQLException e){
            e.printStackTrace();
        }

        response.sendRedirect("posts");

    }

}