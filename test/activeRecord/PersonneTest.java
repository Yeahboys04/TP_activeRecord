package activeRecord;

import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public class PersonneTest {



    @BeforeEach
    void avant() throws SQLException, ClassNotFoundException{
        Personne.createTable();
    }


    public void testSave(){

    }
}
