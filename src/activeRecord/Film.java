package activeRecord;

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
}
