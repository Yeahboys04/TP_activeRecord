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


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void save() throws SQLException {
        if(id > 0){
            this.update();
        }else{
            this.saveNew();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    private void update() throws SQLException {
        System.out.println("update");
        Connection connection = DBConnection.getInstance().getConnection();
        String SQLprep = "update Personne set nom=?, prenom=? where id=?;";
        PreparedStatement prep1 = connection.prepareStatement(SQLprep);
        prep1.setString(1, this.nom);
        prep1.setString(2, this.prenom);
        prep1.setInt(3, this.id);
        prep1.execute();

    }

    private void saveNew() throws SQLException {
        Connection connect =DBConnection.getInstance().getConnection();
        String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
        PreparedStatement prep ;
        prep = connect.prepareStatement(SQLPrep,Statement.RETURN_GENERATED_KEYS);
        prep.setString(1,this.nom);
        prep.setString(2, this.prenom);
        prep.executeUpdate();
        ResultSet rs = prep.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }
        System.out.println("**** id utilise lors de l'ajout ****");
        System.out.println(this.id);
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
            p.setId(id);
            personnes.add(p);
            System.out.println("->("+ id + ")"+ nom + ","+ prenom);
        }

        return personnes;
    }


    public static Personne findById(int id) throws SQLException {

        Connection dbconnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbconnection.createStatement();
        String query = "SELECT * FROM Personne WHERE id = ?";
        PreparedStatement prep = dbconnection.prepareStatement(query);
        prep.setInt(1, id);
        ResultSet resultSet = prep.executeQuery();
        if(resultSet.next()){
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            Personne p = new Personne(nom, prenom);
            p.id= id;
            return p;
        } else {
            return  null;
        }
    }

    public static ArrayList<Personne> findByName(String name) throws SQLException {
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection dbConnection = DBConnection.getInstance().getConnection();
        Statement statement = dbConnection.createStatement();
        String query = "SELECT * FROM Personne WHERE nom = ?";
        PreparedStatement prep = dbConnection.prepareStatement(query);
        prep.setString(1, name);
        ResultSet resultSet = prep.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            Personne p = new Personne(nom, prenom);
            p.setId(id);
            personnes.add(p);
            System.out.println("->("+ id + ")"+ nom + ","+ prenom);
        }

        return personnes;
    }

    public void delete() throws SQLException {
        Connection dbConnection = DBConnection.getInstance().getConnection();
        Statement statement = dbConnection.createStatement();
        String query = "DELETE FROM Personne WHERE id = ?";
        PreparedStatement prep = dbConnection.prepareStatement(query);

        prep.setInt(1, this.id);
        prep.executeUpdate();
        this.id = -1;
    }


    public static void createTable() throws SQLException , ClassNotFoundException {
        System.out.println("Create table");
        Connection connect = DBConnection.getInstance().getConnection();
        String createString = "CREATE TABLE Personne ( "
                + "ID INTEGER  AUTO_INCREMENT, " + "NOM varchar(40) NOT NULL, "
                + "PRENOM varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
    }


    public static void deleteTable() throws SQLException, ClassNotFoundException{
        Connection connect = DBConnection.getInstance().getConnection();
        String drop = "DROP TABLE Personne";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
    }


    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
