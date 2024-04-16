import java.util.LinkedHashMap;

public class Arbre {
    protected int freq;
    protected String car;
    protected Arbre filsDroit;
    protected Arbre filsGauche;

    //Constructors
    public Arbre(int freq, String car) {
        this.car = car;
        this.freq = freq;
    }

    public Arbre(int freq, Arbre filsDroit, Arbre filsGauche) {
        this.freq = freq;
        this.filsDroit = filsDroit;
        this.filsGauche = filsGauche;
    }   

    //Methods

    public void createListeFeuille(LinkedHashMap<String, Integer> dicoFreq) {

    }
}
