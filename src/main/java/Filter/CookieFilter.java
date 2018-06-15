package Filter;


import beans.UserAccount;
import utils.DBUtils;
import utils.MysqlUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

    /*public CookieFilter() {
    }*/

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();


        UserAccount userInSession = MysqlUtils.getLoginedUser(session);
        //
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        // Connection создан в JDBCFilter.
        Connection conn = MysqlUtils.getStoredConnection(request);

        // Флаг(flag) для проверки Cookie.
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userName = MysqlUtils.getUserNameInCookie(req);
            try {
                UserAccount user = DBUtils.findUser(conn, userName);
                MysqlUtils.storeLoginedUser(session, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Отметить проверенные Cookie.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(request, response);
    }

}