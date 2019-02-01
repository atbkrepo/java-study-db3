import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudCmdApplication {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Param should be only 1");
        }
        String sql = args[0];
        CrudEnum operation;

        if (sql.toUpperCase().startsWith(CrudEnum.SELECT.name())) {
            operation = CrudEnum.SELECT;
        } else if (sql.toUpperCase().startsWith(CrudEnum.INSERT.name())) {
            operation = CrudEnum.INSERT;
        } else if (sql.toUpperCase().startsWith(CrudEnum.UPDATE.name())) {
            operation = CrudEnum.UPDATE;
        } else if (sql.toUpperCase().startsWith(CrudEnum.DELETE.name())) {
            operation = CrudEnum.DELETE;
        } else {
            throw new IllegalArgumentException("Argument should contain one of the SQL operations:SELECT,INSERT,UPDATE,DELETE");
        }

        try (Connection conn = JdbcRoutine.getConnection()) {
            if (operation == CrudEnum.SELECT) {
                try (ResultSet sqlResult = JdbcRoutine.select(conn, sql)) {
                    JdbcRoutine.printResultSet(sqlResult);
                }
            } else if (operation == CrudEnum.INSERT) {
                try {
                    int count = JdbcRoutine.insert(conn, sql);
                    System.out.println(count +" row(s) added");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (operation == CrudEnum.UPDATE) {
                try {
                    int count = JdbcRoutine.insert(conn, sql);
                    System.out.println(count +" row(s) updated");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (operation == CrudEnum.DELETE) {
                try {
                    int count = JdbcRoutine.insert(conn, sql);
                    System.out.println(count +" row(s) deleted");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//"SELECT * FROM PERSON"
//"DELETE FROM PERSON WHERE ID = 1"
//"insert into person(id,name,salary)values(1,'Lucy',5.25)"
//"update person set salary = 125 where id =1"