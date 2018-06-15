package servlets;

import beans.Comment;
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
@WebServlet(urlPatterns = { "/editComments"})
public class EditComments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MysqlUtils.getStoredConnection(request);

        String id = request.getParameter("id");
        String postId = request.getParameter("postId");

        Comment editComments = null;
        String errorString = null;

        try {
            editComments = DBUtils.findComment(conn, id, postId);

        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).
        if (errorString != null && editComments == null) {
            response.sendRedirect(request.getServletPath() + "/PostViews/PostView");
            return;
        }

        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("editComments", editComments);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/PostViews/PostView.jsp");
        dispatcher.forward(request, response);

    }
    // После того, как пользователь отредактировал информацию продукта и нажал на Submit.
    // Данный метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MysqlUtils.getStoredConnection(request);

        String code = request.getParameter("id");
        String userName = request.getParameter("userName");
        String content = request.getParameter("content");
        String idPost = request.getParameter("postId");
        int id = 0;
        int postId = 0;
        try {
            id = Integer.parseInt(code);
        } catch (Exception e) {
        }
        try {
            postId = Integer.parseInt(idPost);
        } catch (Exception e) {
        }
        Comment upComments = new Comment(id, content, userName,postId);

        String errorString = null;

        try {
            DBUtils.updateComments(conn, upComments);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("editComments", upComments);

        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/PostViews/PostView.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect к странице со списком продуктов.
        else {
            response.sendRedirect(String.format("post?id=%d", postId));
        }
    }
}
