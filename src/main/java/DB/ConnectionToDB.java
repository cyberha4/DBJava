package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by admin on 16.02.2017.
 */
public class ConnectionToDB {
    private static final String url = "jdbc:mysql://localhost:3306/java";
    private static final String user = "root";
    private static final String password = "";

    private static Connection instance;

    private ConnectionToDB(){
        try {
            instance = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance(){
        if(instance == null){
            new ConnectionToDB();
        }
        return instance;
    }
}
