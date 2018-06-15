package servlets.PostControllers;

import beans.Comment;
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
@WebServlet("/post")
*/
@WebServlet(urlPatterns = { "/post" })

public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MysqlUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        UserAccount loginedUser = MysqlUtils.getLoginedUser(session);
        int postId;

        // Если не залогинен - кидает шоб логинился
        if (loginedUser == null) {
            request.setAttribute("msgError", "It's necessary to be logged in to load that page.");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        try {
            postId = Integer.parseInt(request.getParameter("id"));
            System.out.println(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);  // 404 page
            return;
        }

        try {
            request.setAttribute("postq", DBUtils.retrieve(conn,postId));
       /*     List<Post> postq = DBUtils.retrieve(conn,postId);
            request.setAttribute("postq", postq);*/
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        try {
            List<Comment> comments = DBUtils.retrieveComment(conn,postId);
            request.setAttribute("comments", comments);

        } catch (SQLException e) {
            e.printStackTrace();
        }
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/PostViews/PostView.jsp");
            dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = MysqlUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        UserAccount loginedUser = MysqlUtils.getLoginedUser(session);
        int postId;

        // Если не залогинен - кидает шоб логинился
        if (loginedUser == null) {
            request.setAttribute("msgError", "It's necessary to be logged in to load that page.");
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }
        try {
            postId = Integer.parseInt(request.getParameter("postId"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }
        // Если не написал коммент - не отправишь.
        String comment   = request.getParameter("comment");
        if (comment == null || comment.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400
            return;
        }

        // добавляешь коммент, имя пользователя и пост айди( к какому посту коммент)
        Comment cmnt = new Comment(comment, loginedUser.getUserName(),postId);
        //Если благополучно вставил коммент - перенаправляет.
        try {
            DBUtils.insertComment(conn,cmnt);

        } catch (SQLException e ){
            e.printStackTrace();
        }
        response.sendRedirect(String.format("post?id=%d", postId));

    }

}