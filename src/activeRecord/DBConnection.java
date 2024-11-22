package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance; // Instance unique
    private Connection connection;
    private String dbName = "activeRecord";
    private String userName = "korban2u";
    private String password = "Vachier2!";
    private String serverName = "localhost";
    private String portNumber = "3306";

    private DBConnection() throws SQLException {
        this.createConnection();
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setNomDB(String nomDB) throws SQLException {
        if (!this.dbName.equals(nomDB)) {
            this.dbName = nomDB;
            this.connection.close();
            this.createConnection();
        }
    }

    private void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String urlDB = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            this.connection = DriverManager.getConnection(urlDB, connectionProps);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC non trouvé", e);
        }
    }
}
