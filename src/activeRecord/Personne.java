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


    public void setId(int id){
        this.id = id;
    }



    public void save(){
        if(id > 0){
            this.update();
        }else{
            this.saveNew();
        }
    }


    private void update(){

    }

    private void saveNew() throws SQLException {
        Connection conect =DBConnection.getInstance().getConnection();
        String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
        PreparedStatement prep;
        prep = connect.prepareStatement(SQLPrep,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, "Steven");
        prep.setString(2, "Spielberg");
        prep.executeUpdate();

        SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
        //l'option RETURN_GENERATED_KEYS permet de récupérer l'id auto increment
        prep = connect.prepareStatement(SQLPrep,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.nom);
        prep.setString(2, this.prenom);
        prep.executeUpdate();
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
        ResultSet resultSet = stmt.executeQuery(query);




    }



}
