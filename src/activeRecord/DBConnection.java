package activeRecord;

import java.sql.Connection;

public class DBConnection {
    private DBConnection dbConnection;

    private DBConnection(DBConnection dbConnection){
        this.dbConnection =  dbConnection;
    }

    public DBConnection DBConnection(){

    }



    public setNomDB(String nomDB){
        Connection connection = new Connection();

    }
}
