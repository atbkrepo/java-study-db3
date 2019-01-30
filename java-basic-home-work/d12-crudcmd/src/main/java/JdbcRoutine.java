import java.sql.*;

public class JdbcRoutine {
    private static final String databaseUrl = "jdbc:sqlite:E:\\owner\\gDrive\\JAVA-STUDY\\java-study-db3\\java-basic-home-work\\d12-crudcmd\\src\\main\\resources\\test.db";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(databaseUrl);
        return connection;
    }

    public static ResultSet select(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    private static int printHeader(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i < columnCount + 1; i++) {
            System.out.printf("%-20s", metaData.getColumnName(i).toUpperCase());
        }
        return columnCount;
    }

    public static void printResultSet(ResultSet resultSet) throws SQLException {
        int columnCount = printHeader(resultSet);
        System.out.println("\n--------------------------------------------------");
        while (resultSet.next()) {
            for (int i = 1; i < columnCount+1; i++) {
                System.out.printf("%-20s", String.valueOf(resultSet.getObject(i)));

            }
            System.out.println();
        }
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
