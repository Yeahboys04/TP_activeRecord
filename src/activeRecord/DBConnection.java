package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static DBConnection instance;

    private Connection connection;
    private String connectionProps;


    private DBConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/activeRecord","korban2u", "Vachier2!");
    }

    public static synchronized DBConnection getInstance() throws SQLException {
        if(instance == null) instance = new DBConnection();
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}
