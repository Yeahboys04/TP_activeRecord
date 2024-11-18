package activeRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Personne {

    private int id;
    private String nom;
    private String prenom;


    public Personne( String nom , String prenom){
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }


    public static ArrayList<Personne> findAll() throws SQLException {
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection dbConnection = DBConnection.getInstance().getConnection();
        Statement statement = dbConnection.createStatement();
        String query = "SELECT * FROM Personne";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            Personne p = new Personne(nom, prenom);
            personnes.add(p);
        }
        statement.close();
        return personnes;
    }


    public static Personne findById(int id) throws SQLException {
        Connection dbconnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbconnection.createStatement();
        String query = "SELECT * FROM Personne WHERE id =?";


    }



}
