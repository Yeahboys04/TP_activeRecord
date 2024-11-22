package activeRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PersonneTest {



    @BeforeEach
    void avant() throws SQLException, ClassNotFoundException{
        Personne.createTable();
    }

    @Test
    public void testSaveNouvellePersonne()throws SQLException, ClassNotFoundException{
        Personne personne = new Personne("Toto", "tata");
        personne.save();
        //Vérification des résultats (test à améliorer)
        assertTrue(personne.getId()>0,"L'id devrait être supérieur à 0");



    }



    @Test
    public void testSaveMiseAJour()throws SQLException , ClassNotFoundException{
        Personne personne = new Personne("tata", "TATA");
        personne.save();
        personne.setNom("Titi");
        personne.save();
        int id = personne.getId();
        assertEquals("TATA", personne.getNom(), "Le nom doit etre tata");

    }


    @BeforeEach
    void apres() throws SQLException {
        Personne.deleteTable();
    }
}
