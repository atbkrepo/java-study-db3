package com.luxoft.userstore.servlet;

import com.luxoft.userstore.db.JdbcRoutine;
import com.luxoft.userstore.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        List<User> users=null;
        try (
            Connection connection = JdbcRoutine.getConnection()
        ) {
            ResultSet resultSet = JdbcRoutine.select(connection,"select id, firstName,lastName,salary,strftime('%Y/%m/%d', dateOfBirth / 1000) from user");
            users=  JdbcRoutine.getResultSetTr(resultSet);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        Map<String,Object>paramMap = new HashMap<>();
        paramMap.put("users",users);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("users.html",paramMap);
        response.getWriter().println(page);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
