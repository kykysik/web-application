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

@WebServlet(urlPatterns = { "/deleteComments"})

public class DeleteComments extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        MysqlUtils mysqlUtils = new MysqlUtils();

        DBUtils db = new DBUtils();
        Connection conn = mysqlUtils.getStoredConnection(request);

        String id = request.getParameter("id");
        String postId = request.getParameter("postId");

        int post = 0;
        try {
            post = Integer.parseInt(postId);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            db.deleteComments(id, postId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).

        response.sendRedirect(String.format("post?id=%d", post));


    }
    // После того, как пользователь отредактировал информацию продукта и нажал на Submit.
    // Данный метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);

    }
}
