package activeRecord;


import java.sql.*;

public class Film {
    String titre;
    int id;
    int id_real;

    public Film(String titre, Personne real){
        this.titre = titre;
        this.id_real = real.getId();
        this.id = -1;
    }
    private Film(String titre, int id, int id_real) {
        this.titre = titre;
        this.id = id;
        this.id_real = id_real;
    }

    public static void createTable() throws SQLException {
        System.out.println("Create table");
        Connection connect = DBConnection.getInstance().getConnection();
        String createString = "CREATE TABLE Film ( "
                + "id INTEGER AUTO_INCREMENT, "
                + "titre VARCHAR(40) NOT NULL, "
                + "id_rea INT(11) DEFAULT NULL, "
                + "PRIMARY KEY (id), "
                + "CONSTRAINT film_ibfk_1 FOREIGN KEY (id_rea) REFERENCES Personne(id))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
    }


    public static void deleteTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String drop = "DROP TABLE Film";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
    }

    public static Film findById(int id) throws SQLException {

        Connection dbconnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbconnection.createStatement();
        String query = "SELECT * FROM Film WHERE id = ?";
        PreparedStatement prep = dbconnection.prepareStatement(query);
        prep.setInt(1, id);
        ResultSet resultSet = prep.executeQuery();
        if(resultSet.next()){
            return new Film(resultSet.getString("titre"), id, resultSet.getInt("id_rea"));
        } else {
            return  null;
        }
    }

    public String getTitre(){
        return titre;
    }

    public int getId(){
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Personne save() throws SQLException {
        Connection dbconnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbconnection.createStatement();
        String query = "INSERT INTO Film (titre, id_rea) VALUES (?, ?)";
        try {
            PreparedStatement prep = dbconnection.prepareStatement(query);
            prep.setString(1, titre);
            prep.setInt(2, id_real);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Personne p = Personne.findById(id_real);
        if(p == null){
            throw new RealisateurAbsentException("Realisateur introuvable");
        } else {
            return p;
        }
    }

    public Personne getRealisateur() {
        try {
            return Personne.findById(id_real);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setTitre(String test2) {
        this.titre = test2;
    }
}

