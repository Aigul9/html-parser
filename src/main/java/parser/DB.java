package parser;

import java.sql.*;
import java.util.*;

import static parser.Constants.*;

/** Class that allows to perform different operations with database with properties:
 * <b>db</b>, <b>username</b>, <b>password</b>, <b>conn</b>.
 * @author Aigul Sibgatullina
 */
public class DB implements Actions {
    private String db = DB;
    private String username = USERNAME;
    private String password = PASSWORD;
    private Connection conn = DriverManager.getConnection(DB, USERNAME, PASSWORD);

    public DB() throws SQLException { }

    public DB(String db, String username, String password) throws SQLException {
        this.db = db;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try (Connection conn = DriverManager.getConnection(db, username, password)) {
            this.conn = conn;
        } catch (SQLException e) {
            Log.logError(e);
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            Log.logError(e);
        }
    }

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
            Log.logError(e);
        }
    }

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

                display(words);
            } else {
                System.out.println("No results");
            }

            stmt.close();
            res.close();
        } catch (SQLException e) {
            Log.logError(e);
        }
    }

    public void deleteFromDB() {
        try {
            String query = String.format("%s %s", "delete from", TABLE_NAME);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException e) {
            Log.logError(e);
        }
    }
}
