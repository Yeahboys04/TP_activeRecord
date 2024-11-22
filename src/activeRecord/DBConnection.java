package activeRecord;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class DBConnection {
    private static DBConnection instance;

    private Connection connection;



    private DBConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/testpersonne","root", "root");
    }

    public static synchronized DBConnection getInstance() throws SQLException {
        if(instance == null) instance = new DBConnection();
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }




}

//
//
//package activeRecord;
//
//import java.sql.Array;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//
//public class DBConnection {
//    private static DBConnection instance;
//
//    private static Connection connection;
//    private String connectionProps;
//
//    private static String url;
//    private static String user;
//    private static String password;
//
//
//    private DBConnection() throws SQLException {
//        connection = DriverManager.getConnection(url,user, password);
//    }
//
//    public static synchronized DBConnection getInstance() throws SQLException {
//        if(instance == null) instance = new DBConnection();
//        return instance;
//    }
//    public Connection getConnection(){
//        return connection;
//    }
//
//    public static void setConfig(HashMap<String,String> config){
//        url = "jdbc:mysql://" + config.get("serverName") + ":" + config.get("portNumber") + "/" + config.get("dbName");
//        user = config.get("userName");
//        password = config.get("password");
//
//    }
//
//
//}
