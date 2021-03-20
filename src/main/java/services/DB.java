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
    /** Database port. */
    private String port = PORT;
    /** Name of the database. */
    private String db = format(PORT, DB_NAME);
    /** Username that allows to get an access to the database. */
    private String username = USERNAME;
    /** Password that allows to get an access to the database. */
    private String password = PASSWORD;

    /** Creates new object of DB with default database name, port and user credentials.
     */
    public DB() { }

    /** Creates new object of DB with given database name and user credentials
     * and establish connection.
     * @param port Database port.
     * @param dbName Name of the database.
     * @param username Username that allows to get an access to the database.
     * @param password Password that allows to get an access to the database.
     */
    public DB(String port, String dbName, String username, String password) {
        this.port = port;
        this.db = format(port, dbName);
        this.username = username;
        this.password = password;
    }

    /** Creates URL-address of the database with UTF-8 encoding
     * that supports different languages.
     * @param port Database port.
     * @param dbName Name of the database.
     * @return URL in a string format.
     */
    public String format(String port, String dbName) {
        return String.format("%s%s%s%s%s", "jdbc:mysql://localhost:" , port, "/", dbName,
                "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8");
    }

    /** Creates a table for storing statistics if it does not exist yet.
     */
    public void createNewTable() {
        try(Connection conn = DriverManager.getConnection(db, username, password)) {
            String sql = "create table if not exists wordscount (" +
                    "id integer primary key auto_increment," +
                    "word nvarchar(255)," +
                    "amount integer)";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            services.Log.logError(e.toString());
        }
    }

    /** Creates a query with "insert" statement
     * and writes every entry of the map into a table.
     * @param words Map with two fields: String key and Integer value.
     */
    public void writeToDB(Map<String, Integer> words) {
        try(Connection conn = DriverManager.getConnection(db, username, password)) {
            String query = "insert into wordscount (word, amount) values(?, ?)";
            createNewTable();
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
        try(Connection conn = DriverManager.getConnection(db, username, password)) {
            String query = "select * from wordscount";
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
        try(Connection conn = DriverManager.getConnection(db, username, password)) {
            String query = "delete from wordscount";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            stmt.close();
        } catch (SQLException e) {
            Log.logError(e.toString());
        }
    }
}
