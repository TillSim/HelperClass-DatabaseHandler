import helpers.ConsoleLogger;
import helpers.DatabaseHandler;

import java.sql.ResultSet;

/**
 * <b>Test</b> class for testing <b>DatabaseHandler</b>.
 */
public class Test {

    public static void main(String[] args) {

        DatabaseHandler connection = new DatabaseHandler("test.db");

        connection.modifyDB("CREATE TABLE IF NOT EXISTS staff "+
                "(id INTEGER PRIMARY KEY NOT NULL, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL)");

        connection.modifyDB("INSERT INTO staff (name, age, email, password) VALUES" +
                "('John', 26, 'john@marston.com', 'abigail')" +
                ",('Arthur', 36, 'arthur@morgan.com', 'mary')");
        printResultSet(connection.queryDB("SELECT * FROM staff WHERE age > 21"));

        connection.modifyDB("DELETE FROM staff WHERE id = 1");
        printResultSet(connection.queryDB("SELECT * FROM staff WHERE id = 2"));

    }


    /**
     * prints columns (name,age) of result set to stream
     * @param resultSet
     */
    private static void printResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("age"));
            }
        } catch (Exception e) {
            ConsoleLogger.printLog("ResultSet empty");
        }

    }
}
