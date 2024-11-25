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

    public String getTitre(){
        return titre;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getRealisateur(){

    }


    public Film findfilmById(int id)throws SQLException{
        Connection dbconnection = DBConnection.getInstance().getConnection();
        Statement stmt = dbconnection.createStatement();
        String query = "SELECT * FROM FILM WHERE id = ?";
        PreparedStatement pstmt = dbconnection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next()){
            String titre = resultSet.getString("titre");
            int id_rea = resultSet.getInt("id_rea");
            Film f = new Film(titre ,new Personne());
        }



    }






}
