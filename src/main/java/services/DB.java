package services;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

import parser.Actions;
import static env.Constants.*;

/** Allows to perform operations with database that have following properties:
 * db, username, password, conn.
 */
public class DB implements Actions {
    /** Name of the database. */
    private String db = format(DB_NAME);
    /** Username that allows to get an access to the database. */
    private String username = USERNAME;
    /** Password that allows to get an access to the database. */
    private String password = PASSWORD;
    /** Connection to the database. */
    private Connection conn = null;

    /** Creates new object of DB with default database name and user credentials.
     */
    public DB() { }

    /** Creates new object of DB with given database name and user credentials.
     * @param dbName Name of the database.
     * @param username Username that allows to get an access to the database.
     * @param password Password that allows to get an access to the database.
     */
    public DB(String dbName, String username, String password) {
        this.db = format(dbName);
        this.username = username;
        this.password = password;
    }

    /** Creates URL-address of the database with UTF-8 encoding
     * that supports different languages.
     * @param dbName Name of the database.
     * @return URL in a string format.
     */
    public String format(String dbName) {
        return String.format("%s%s%s", "jdbc:mysql://localhost:3306/", dbName,
                "?useUnicode=true&characterEncoding=utf-8");
    }

    /** Checks if connection could be established.
     */
    public void connect() {
        try (Connection conn = DriverManager.getConnection(db, username, password)) {
            this.conn = conn;
        } catch (SQLException e) {
            services.Log.logError(e.toString());
        }
    }

    /** Closes the connection.
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            services.Log.logError(e.toString());
        }
    }

    /** Creates a query with "insert" statement
     * and writes every entry of the map into a table.
     * @param words Map with two fields: String key and Integer value.
     */
    public void writeToDB(Map<String, Integer> words) {
        try {
            String query = String.format("%s %s %s", "insert into", TABLE_NAME, "(word, amount) values(?, ?)");
            PreparedStatement stmt = conn.prepareStatement(query);
            for (Map.Entry<String, Integer> word: words.entrySet()) {
                stmt.setString(1, word.getKey());
                stmt.setString(2, String.valueOf(word.getValue()));
                stmt.execute();
            }

            stmt.close();
        } catch (SQLException e) {
            services.Log.logError(e.toString());
        }
    }

    /** Retrieves data from a specified table.
     */
    public void getFromDB() {
        try {
            String query = String.format("%s %s", "select * from", TABLE_NAME);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            Map<String, Integer> words = new TreeMap<>();
            if (res.isBeforeFirst()) {
                while (res.next()) {
                    words.put(res.getString("word"), res.getInt("amount"));
                }

                Actions.display(words);
            } else {
                System.out.println("No results");
            }

            stmt.close();
            res.close();
        } catch (SQLException e) {
            services.Log.logError(e.toString());
        }
    }

    /** Clears a specified table.
     */
    public void deleteFromDB() {
        try {
            String query = String.format("%s %s", "delete from", TABLE_NAME);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException e) {
            Log.logError(e.toString());
        }
    }
}
