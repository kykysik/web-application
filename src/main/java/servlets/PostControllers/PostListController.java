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
import java.util.List;

/*
@WebServlet("/posts")
*/
@WebServlet(urlPatterns = { "/posts" })

public class PostListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBUtils db = new DBUtils();
        MysqlUtils mysqlUtils = new MysqlUtils();

        Connection conn = mysqlUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        // Проверить, вошел ли пользователь в систему (login) или нет.
        UserAccount loginedUser = mysqlUtils.getLoginedUser(session);
        if (loginedUser == null) {
            request.setAttribute("msgError", "It's necessary to be logged in to load that page.");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        //Список постов
        try {
            List<Post> posts = db.listPost();
            request.setAttribute("postList", posts);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/PostViews/PostListView.jsp");
        dispatcher.forward(request, response);
    }


}