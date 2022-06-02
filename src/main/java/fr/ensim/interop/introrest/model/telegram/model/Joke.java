package fr.ensim.interop.introrest.model.telegram.model;

public class Joke {
    private int id;
    private String titre;
    private String texte;
    private int rate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    static int nbJoke=0;

    public Joke(String titre, String texte,int rate) {
        nbJoke++;
        this.titre = titre;
        this.texte = texte;
        this.rate=rate;
    }

    public Joke(int id, String titre, String texte,int rate) {
        this.id = id;
        this.titre = titre;
        this.texte = texte;
        this.rate=rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
