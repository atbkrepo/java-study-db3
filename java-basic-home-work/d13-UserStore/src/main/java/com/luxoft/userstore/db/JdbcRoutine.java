package com.luxoft.userstore.db;

import com.luxoft.userstore.servlet.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class JdbcRoutine {
    private static final String databaseUrl = "jdbc:sqlite:E:/owner/gDrive/JAVA-STUDY/java-study-db3/java-basic-home-work/d13-UserStore/src/main/resources/db/userstore.db";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseUrl);
        return connection;
    }

    public static ResultSet select(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }


    public static List<User> getResultSetTr(ResultSet resultSet) throws SQLException {
        List rows = new ArrayList(5);

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setSalary(resultSet.getDouble(4));
            user.setDateOfBirth(resultSet.getString(5));
            rows.add(user);
        }
        return rows;
    }
    //=====================
    private static int executeUpdate(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        int count = statement.executeUpdate(sql);
        return count;
    }
    public static int insert(Connection connection, String sql) throws SQLException {
        return executeUpdate(connection,sql);
    }
    public static int update(Connection connection, String sql) throws SQLException {
        return executeUpdate(connection,sql);
    }
    public static int delete(Connection connection, String sql) throws SQLException {
        return executeUpdate(connection,sql);
    }
}
