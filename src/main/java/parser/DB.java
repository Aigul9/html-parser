package parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static parser.Constants.*;

public class DB {
    private Map<String, Integer> words;
    private String db = DB;
    private String username = USERNAME;
    private String password = PASSWORD;

    public DB() { }

    public DB(String db, String username, String password) {
        this.db = db;
        this.username = username;
        this.password = password;
    }

    public String connect() {
        try (Connection connection = DriverManager.getConnection(db, username, password)) {
            return "OK";
        } catch (SQLException e) {
            return(e.toString());
        }
    }

//    public writeToDB() {
//
//    }
}
