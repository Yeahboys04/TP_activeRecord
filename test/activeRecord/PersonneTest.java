package activeRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PersonneTest {


    private Personne personne1;
    private Personne personne2;

    @BeforeEach
    public void avant() throws SQLException, ClassNotFoundException{
        System.out.println("before");
        Personne.createTable();
        personne1 = new Personne("Korban", "Ryan");
        personne1.save();
        personne2= new Personne("Salvo", "Luka");
        personne2.save();
    }

    @Test
    public void testSaveNouvellePersonne()throws SQLException, ClassNotFoundException{
        System.out.println("testNouvellePrsn");

        Personne personne = new Personne("Toto", "tata");
        personne.save();
        //Vérification des résultats (test à améliorer)
        assertTrue(personne.getId()>0,"L'id devrait être supérieur à 0");



    }



    @Test
    public void testSaveMiseAJour()throws SQLException , ClassNotFoundException{
        System.out.println("test SaveMaj");
        personne1.setNom("Titi");
        personne1.save();
        int id = personne1.getId();
        Personne perso3 = Personne.findById(id);
        assertEquals("Titi", perso3.getNom(), "Le nom doit etre titi"); // A ameliorer
    }


    @Test
    public void testFindAll()throws SQLException, ClassNotFoundException{
        System.out.println("test FindAll");
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        personnes = Personne.findAll();
        System.out.println(personnes);
        assertEquals(2, personnes.size(), "Il devrait y avoir 2 personnes ");

    }


    @AfterEach
    public void apres() throws SQLException, ClassNotFoundException {
        Personne.deleteTable();
    }
}
