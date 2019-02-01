package com.luxoft.userstore;

import com.luxoft.userstore.servlet.UsersRequestsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class UserStoreApplication {
    public static void main(String[] args) throws Exception {
        UsersRequestsServlet usersRequestsServlet = new UsersRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(usersRequestsServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}

/*      use DB as user store // User (id, firstName, lastName, salary, dateOfBirth* (LocalDate) )
        GET /users -> show all users as table
        GET /users/add -> show form to add user,
        POST /users/add -> add user
        GET /users/edit?id=* -> show form to edit user with id->*
        POST /users/edit -> edit user
        POST /users/remove -> remove user from store by id // (button should be on /users page)*/
